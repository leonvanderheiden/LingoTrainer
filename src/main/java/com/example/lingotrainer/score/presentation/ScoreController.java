package com.example.lingotrainer.score.presentation;

import com.example.lingotrainer.score.domain.Score;
import com.example.lingotrainer.score.application.ScoreServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ScoreController {

    @Autowired
    public ScoreServiceInterface scoreService;

    @GetMapping("/score/{id}")
    public Score getScoreById(@PathVariable Long id) {
        return scoreService.findById(id);
    }

    /*@PostMapping(
            value = "/score",
            consumes = "application/json",
            produces = "application/json")*/
    @RequestMapping(value = { "/score" }, method = { RequestMethod.POST } )
    public Score saveScore(@RequestBody Score score) {
        return scoreService.save(score);
    }

    @PutMapping("/score/{scoreid}")
    public Score updateScore(@PathVariable Long scoreid, @RequestBody Score score) {
        return scoreService.updateById(scoreid, score);
    }

    @DeleteMapping("/score/{scoreid}")
    public Boolean deleteScore(@PathVariable Long scoreid) {
        return scoreService.deleteById(scoreid);
    }
}
