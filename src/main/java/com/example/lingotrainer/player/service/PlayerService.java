package com.example.lingotrainer.player.service;

import com.example.lingotrainer.player.Player;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService implements PlayerServiceInterface {

    public List<Player> playerList;

    @Override
    public List<Player> getPlayers() {
        Player player1 = new Player("Sjaak afhaak");
        Player player2 = new Player("Charlie lit");
        playerList.add(player1); playerList.add(player2);
        return playerList;
    }

    public Player save(Player player) {
        return player;
    }
}
