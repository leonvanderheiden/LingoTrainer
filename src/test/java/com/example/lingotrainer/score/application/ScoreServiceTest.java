package com.example.lingotrainer.score.application;

import com.example.lingotrainer.score.data.ScoreRepository;
import com.example.lingotrainer.score.domain.Score;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import static org.aspectj.bridge.MessageUtil.fail;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RunWith(MockitoJUnitRunner.class)
public class ScoreServiceTest {

    @Mock
    @Autowired
    private ScoreRepository scoreRepository;

    @InjectMocks
    @Autowired
    private ScoreService scoreService;

    @Test
    @DisplayName("get an existing meeting")
    void getExistingScore() {
        Score SCORE_A = new Score();
        SCORE_A.setScore(40L);

        this.scoreService.save(SCORE_A);
        //Score result = this.scoreService.findById(SCORE_A.getId());

        //assertThat(result).hasFieldOrPropertyWithValue("score", 40L);

        /*result.ifPresentOrElse(
                (scoreResult) -> Assertions.assertEquals(SCORE_A, scoreResult),
                () -> fail("Meeting should have been present")
        );*/
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


}
