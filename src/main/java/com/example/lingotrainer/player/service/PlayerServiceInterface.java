package com.example.lingotrainer.player.service;

import com.example.lingotrainer.player.Player;

import java.util.List;

public interface PlayerServiceInterface {
    List<Player> getPlayers();
    Player save(Player player);
}
