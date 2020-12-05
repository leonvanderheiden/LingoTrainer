package com.example.lingotrainer.player.service;

import com.example.lingotrainer.player.Player;

import java.util.List;

public interface PlayerServiceInterface {
    Player findById(Long id);

    Player findByName(String name);

    Player save(Player player);

    Player update(Long playerid, Player player);

    Boolean deleteById(Long playerid);
}
