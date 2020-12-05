package com.example.lingotrainer.player.service;

import com.example.lingotrainer.player.Player;
import com.example.lingotrainer.player.repository.PlayerRepository;
import com.example.lingotrainer.score.Score;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService implements PlayerServiceInterface {

    private PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player findById(Long id) {
        return playerRepository.findDistinctById(id);
    }

    @Override
    public Player findByName(String name) {
        System.out.println(playerRepository.findByNameIs(name) + " repository");
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
            //Als dat niet het geval is wordt er een nieuwe user aangemaakt
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
        Player p = playerRepository.findDistinctById(playerid);

        p.setName(player.getName());
        p.setPassword(player.getPassword());

        return playerRepository.save(p);
    }

    @Override
    public Boolean deleteById(Long playerid) {
        return playerRepository.deleteDistinctById(playerid);
    }
}
