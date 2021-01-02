package com.example.lingotrainer.score.domain;

import com.example.lingotrainer.round.domain.Round;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreTest {

    @Test
    @DisplayName("Set id of a score object")
    public void setIdTest() {
        long id = 1000;
        Score score = new Score();
        score.setId(id);
        assertEquals(score.getId(), id);
    }

    @Test
    @DisplayName("Get id of a score object")
    public void getIdTest() throws NoSuchFieldException, IllegalAccessException {

        Score score = new Score();
        final Field field = score.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(score, 1L);

        long result = score.getId();

        assertEquals(1L, result);
    }

    @Test
    @DisplayName("Set score of a score object")
    public void setScoreTest() {
        long score = 1000;
        Score scoreObj = new Score();
        scoreObj.setScore(score);
        assertEquals(scoreObj.getScore(), score);
    }

    @Test
    @DisplayName("Get score of a score object")
    public void getScoreTest() throws NoSuchFieldException, IllegalAccessException {

        Score scoreObj = new Score();
        final Field field = scoreObj.getClass().getDeclaredField("score");
        field.setAccessible(true);
        field.set(scoreObj, 100L);

        long result = scoreObj.getScore();

        assertEquals(100L, result);
    }
}
