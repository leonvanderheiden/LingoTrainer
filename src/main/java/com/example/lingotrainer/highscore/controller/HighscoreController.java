package com.example.lingotrainer.highscore.controller;

import com.example.lingotrainer.highscore.service.HighscoreServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HighscoreController {

    @Autowired
    public HighscoreServiceInterface highscoreService;
}
