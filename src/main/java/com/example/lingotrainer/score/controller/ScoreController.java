package com.example.lingotrainer.score.controller;

import com.example.lingotrainer.score.service.ScoreServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScoreController {

    @Autowired
    public ScoreServiceInterface scoreService;
}
