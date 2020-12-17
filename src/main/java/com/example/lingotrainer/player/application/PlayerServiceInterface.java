package com.example.lingotrainer.player.application;

import com.example.lingotrainer.player.domain.Player;

public interface PlayerServiceInterface {
    Player findById(Long id);

    Player findByName(String name);

    Player findByNameAndPassword(Player player);

    Player save(Player player);

    Player updateById(Long playerid, Player player);

    Boolean deleteById(Long playerid);
}
