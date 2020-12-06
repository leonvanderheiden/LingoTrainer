package com.example.lingotrainer.game.service;

import com.example.lingotrainer.game.Game;
import org.springframework.web.bind.annotation.PathVariable;

public interface GameServiceInterface {

    Game getGame(Long id);

    Game save(Game game);

    Game saveGameToPlayer(Long playerid, Game game);

    Game updateById(Long gameid, Game game);

    Boolean deleteById(Long gameid);
}
