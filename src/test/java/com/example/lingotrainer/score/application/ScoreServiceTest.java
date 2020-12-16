package com.example.lingotrainer.score.application;

import com.example.lingotrainer.score.data.ScoreRepository;
import com.example.lingotrainer.score.domain.Score;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.aspectj.bridge.MessageUtil.fail;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Tag("integration")
public class ScoreServiceTest {
    private Score SCORE_A = new Score(0L, 40L);

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private ScoreRepository scoreRepository;

    @Test
    @DisplayName("get an existing meeting")
    void getExistingScore() {
        scoreService = new ScoreService(scoreRepository);

        System.out.println(scoreRepository);
        this.scoreService.save(SCORE_A);
        Score result = this.scoreService.findById(SCORE_A.getId());

        assertThat(result).hasFieldOrPropertyWithValue("score", 40L);

        /*result.ifPresentOrElse(
                (scoreResult) -> Assertions.assertEquals(SCORE_A, scoreResult),
                () -> fail("Meeting should have been present")
        );*/
    }


}
