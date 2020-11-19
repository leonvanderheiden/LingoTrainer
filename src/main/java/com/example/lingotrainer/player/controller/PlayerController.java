package com.example.lingotrainer.player.controller;

import com.example.lingotrainer.player.Player;
import com.example.lingotrainer.player.service.PlayerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class PlayerController {

    @Autowired
    public PlayerServiceInterface playerService;

    @GetMapping("/players")
    public List<Player> getPlayers() {
        return playerService.getPlayers();
    }

    @PostMapping(
            value = "/player",
            consumes = "application/json",
            produces = "application/json")
    public Player savePlayer(@RequestBody Player player) {
        //if (result.hasErrors()) {
        //    throw new ProductSaveNotValid();
        //}
        return playerService.save(player);
    }
}
