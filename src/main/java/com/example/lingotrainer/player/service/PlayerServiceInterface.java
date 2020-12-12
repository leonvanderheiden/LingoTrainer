package com.example.lingotrainer.player.service;

import com.example.lingotrainer.player.domain.Player;

public interface PlayerServiceInterface {
    Player findById(Long id);

    Player findByName(String name);

    Player findByNameAndPassword(Player player);

    Player save(Player player);

    Player update(Long playerid, Player player);

    Boolean deleteById(Long playerid);
}
