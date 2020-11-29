package com.example.lingotrainer.round.controller;

import com.example.lingotrainer.round.Round;
import com.example.lingotrainer.round.service.RoundService;
import com.example.lingotrainer.round.service.RoundServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class RoundController {

    @Autowired
    public RoundServiceInterface roundService;

    @GetMapping("/round/{id}")
    public Round getRound(@PathVariable Long id) {
        return roundService.getRound(id);
    }

    @PostMapping(
            value = "/round",
            consumes = "application/json",
            produces = "application/json")
    public Round saveRound(@RequestBody Round round) {
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
