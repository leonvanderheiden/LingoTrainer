package com.example.lingotrainer.round.controller;

import com.example.lingotrainer.round.Round;
import com.example.lingotrainer.round.service.RoundService;
import com.example.lingotrainer.round.service.RoundServiceInterface;
import com.example.lingotrainer.word.service.WordServiceInterace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class RoundController {

    @Autowired
    public RoundServiceInterface roundService;

    @Autowired
    public WordServiceInterace wordService;

    @GetMapping("/round/{id}")
    public Round getRound(@PathVariable Long id) {
        return roundService.getRound(id);
    }

    @PostMapping(
            value = "/feedback/{attempt}",
            consumes = "application/json",
            produces = "application/json")
    public String getFeedback(@PathVariable String attempt, @RequestBody Round round) {
        return roundService.getFeedback(attempt, round);
    }

    @PostMapping(
            value = "/round",
            consumes = "application/json",
            produces = "application/json")
    public Round saveRound(@RequestBody Round round) {
        return roundService.save(round);
    }

    @PostMapping(
            value = "/newround",
            consumes = "application/json",
            produces = "application/json")
    public Round saveRoundWithWord(@RequestBody Round round) {
        int letters = Character.getNumericValue(round.getRoundType().charAt(0));
        round.setWord(wordService.getRandomWordByLength(Long.valueOf(letters)));
        return roundService.save(round);
    }


    @PutMapping("/round/{roundid}")
    public Round updateRound(@PathVariable Long roundid, @RequestBody Round round) {
        return roundService.updateById(roundid, round);
    }

    @DeleteMapping("/round/{roundid}")
    public Boolean deleteRound(@PathVariable Long roundid) {
        return roundService.deleteById(roundid);
    }
}
