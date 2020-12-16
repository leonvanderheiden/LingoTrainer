package com.example.lingotrainer.round.presentation;

import com.example.lingotrainer.round.domain.Round;
import com.example.lingotrainer.round.application.RoundServiceInterface;
import com.example.lingotrainer.word.application.WordServiceInterace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoundController {

    @Autowired
    public RoundServiceInterface roundService;

    @Autowired
    public WordServiceInterace wordService;

    @GetMapping("/round/{id}")
    public Round getRoundById(@PathVariable Long id) {
        return roundService.findById(id);
    }

    //Geeft feedback op basis van een woordpoging en het gegeven woord in het round object
    @PostMapping(
            value = "/feedback/{attempt}",
            consumes = "application/json",
            produces = "application/json")
    public String getFeedback(@PathVariable("attempt") String attempt, @RequestBody Round round) {
        return roundService.getFeedback(attempt, round);
    }

    @PostMapping(
            value = "/round",
            consumes = "application/json",
            produces = "application/json")
    public Round saveRound(@RequestBody Round round) {
        return roundService.save(round);
    }

    //Maakt een nieuw round object met een random word er aan gekoppelt
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
