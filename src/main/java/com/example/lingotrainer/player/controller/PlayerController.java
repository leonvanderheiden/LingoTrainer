package com.example.lingotrainer.player.controller;

import com.example.lingotrainer.player.Player;
import com.example.lingotrainer.player.PlayerDto;
import com.example.lingotrainer.player.service.PlayerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

@RestController
public class PlayerController {

    @Autowired
    public PlayerServiceInterface playerService;

    @Autowired
    private final ModelMapper modelMapper;

    public PlayerController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @GetMapping("/player/{id}")
    public PlayerDto getPlayerById(@PathVariable Long id) {
        return modelMapper.map(playerService.findById(id), PlayerDto.class);
    }

    @GetMapping("/user/{name}")
    public PlayerDto getPlayerById(@PathVariable String name) {
        return modelMapper.map(playerService.findByName(name), PlayerDto.class);
    }

    @PostMapping(
            value = "/player",
            consumes = "application/json",
            produces = "application/json")
    public PlayerDto savePlayer(@RequestBody Player player) {
        return modelMapper.map(playerService.save(player), PlayerDto.class);
    }

    @PostMapping(
            value = "/login",
            consumes = "application/json",
            produces = "application/json")
    public Player login(@RequestBody Player player) {
        return playerService.findByNameAndPassword(player);
    }

    @PutMapping("/player/{playerid}")
    public PlayerDto updatePlayer(@PathVariable Long playerid, @RequestBody Player player) {
        return modelMapper.map(playerService.update(playerid, player), PlayerDto.class);
    }

    @DeleteMapping("/player/{playerid}")
    public Boolean deletePlayer(@PathVariable Long playerid) {
        return playerService.deleteById(playerid);
    }
}
