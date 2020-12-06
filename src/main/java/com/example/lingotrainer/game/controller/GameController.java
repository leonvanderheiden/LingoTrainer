package com.example.lingotrainer.game.controller;


import com.example.lingotrainer.game.Game;
import com.example.lingotrainer.game.service.GameServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
public class GameController {

    @Autowired
    public GameServiceInterface gameService;

    @GetMapping("/game/{id}")
    public Game getGame(@PathVariable Long id) {
        return gameService.getGame(id);
    }

    @PostMapping(
            value = "/game",
            consumes = "application/json",
            produces = "application/json")
    public Game saveGame(@RequestBody Game game) {
        return gameService.save(game);
    }

    @PostMapping(
            value = "/game/{playerid}",
            consumes = "application/json",
            produces = "application/json")
    public Game newGame(@PathVariable Long playerid,@RequestBody Game game) {
        return gameService.saveGameToPlayer(playerid, game);
    }

    @PutMapping("/game/{gameid}")
    public Game updateGame(@PathVariable Long gameid, @RequestBody Game game) {
        return gameService.updateById(gameid, game);
    }

    @DeleteMapping("/game/{gameid}")
    public Boolean deleteGame(@PathVariable Long gameid) {
        return gameService.deleteById(gameid);
    }
}
