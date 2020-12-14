package com.example.lingotrainer.highscore.controller;

import com.example.lingotrainer.highscore.domain.Highscore;
import com.example.lingotrainer.highscore.service.HighscoreServiceInterface;
import com.example.lingotrainer.score.domain.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HighscoreController {

    @Autowired
    public HighscoreServiceInterface highscoreService;

    @GetMapping("/highscore/{id}")
    public Highscore getHighscore(@PathVariable Long id) {
        return highscoreService.findById(id);
    }

    @PostMapping(
            value = "/highscore",
            consumes = "application/json",
            produces = "application/json")
    public Highscore saveHighscore(@RequestBody Highscore highscore) {
        return highscoreService.save(highscore);
    }

    @PutMapping("/highscore/{highscoreid}")
    public Highscore updateHighscore(@PathVariable Long highscoreid, @RequestBody Highscore highscore) {
        return highscoreService.updateById(highscoreid, highscore);
    }

    @DeleteMapping("/highscore/{highscoreid}")
    public Boolean deleteHighscore(@PathVariable Long highscoreid) {
        return highscoreService.deleteById(highscoreid);
    }
}
