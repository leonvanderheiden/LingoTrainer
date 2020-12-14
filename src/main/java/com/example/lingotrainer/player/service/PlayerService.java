package com.example.lingotrainer.player.service;

import com.example.lingotrainer.highscore.domain.Highscore;
import com.example.lingotrainer.highscore.service.HighscoreServiceInterface;
import com.example.lingotrainer.player.domain.Player;
import com.example.lingotrainer.player.data.PlayerRepository;
import com.example.lingotrainer.player.exceptions.PlayerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService implements PlayerServiceInterface {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private HighscoreServiceInterface highscoreService;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player findById(Long playerid) {
        return playerRepository.findById(playerid).orElseThrow(() -> new PlayerNotFoundException(playerid));
    }

    @Override
    public Player findByName(String name) {
        return playerRepository.findByNameIs(name);
    }

    @Override
    public Player findByNameAndPassword(Player player) {

        Player p = null;

        //Controleerd of meegegeven username in db staat
        if (playerRepository.existsByName(player.getName())) {
            //Haalt speler uit de database d.m.v. meegegeven naam
            Player dbUser = playerRepository.findByNameIs(player.getName());

            //Controleerd of meegegeven wachtwoord overeen komt met het wachtwoord in de database
            if (dbUser.getPassword().equals(player.getPassword())) {
                p = dbUser;
            }
        }
        else {
            //Als dat niet het geval is wordt er een nieuwe user aangemaakt met een highscore van 0
            Highscore h = new Highscore(0L);
            player.setHighscore(highscoreService.save(h));
            p = playerRepository.save(player);
        }
        return p;
    }

    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Player update(Long playerid, Player player) {
        Player p = playerRepository.findById(playerid).orElseThrow(() -> new PlayerNotFoundException(playerid));

        p.setName(player.getName());
        p.setPassword(player.getPassword());

        return playerRepository.save(p);
    }

    @Override
    public Boolean deleteById(Long playerid) {
        return playerRepository.deleteDistinctById(playerid);
    }
}
