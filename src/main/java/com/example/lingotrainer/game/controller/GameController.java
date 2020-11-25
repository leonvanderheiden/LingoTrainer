package com.example.lingotrainer.game.controller;


import com.example.lingotrainer.game.service.GameServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @Autowired
    public GameServiceInterface gameService;
}
