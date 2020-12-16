package com.example.lingotrainer.game.application;

import com.example.lingotrainer.game.domain.Game;

public interface GameServiceInterface {

    Game findById(Long id);

    Game save(Game game);

    Game saveGameToPlayer(Long playerid, Game game);

    Game updateById(Long gameid, Game game);

    Boolean deleteById(Long gameid);
}
