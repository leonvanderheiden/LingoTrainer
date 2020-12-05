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
