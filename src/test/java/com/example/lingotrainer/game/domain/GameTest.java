package com.example.lingotrainer.game.domain;

import com.example.lingotrainer.round.domain.Round;
import com.example.lingotrainer.score.domain.Score;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {
    @Test
    @DisplayName("Set id of a game object")
    public void setIdTest() {
        long id = 1000;
        Game game = new Game();
        game.setId(id);
        assertEquals(game.getId(), id);
    }

    @Test
    @DisplayName("Get id of a game object")
    public void getIdTest() throws NoSuchFieldException, IllegalAccessException {

        Game game = new Game();
        final Field field = game.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(game, 1L);

        long result = game.getId();

        assertEquals(1L, result);
    }

    @Test
    @DisplayName("Set score of a game")
    public void setScoreTest() {
        Score score = new Score(1L, 500L);
        Game gameObj = new Game();

        gameObj.setScore(score);

        assertEquals(gameObj.getScore(), score);
    }

    @Test
    @DisplayName("Get score of a game")
    public void getScoreTest() throws NoSuchFieldException, IllegalAccessException {
        Game gameObj = new Game();
        Score score = new Score(1L, 500L);
        final Field field = gameObj.getClass().getDeclaredField("score");
        field.setAccessible(true);
        field.set(gameObj, score);

        Score result = gameObj.getScore();

        assertEquals(score, result);
    }

    @Test
    @DisplayName("Set roundlist of a game")
    public void setRoundsTest() {
        List<Round> roundList = null;
        Game gameObj = new Game();

        gameObj.setRounds(roundList);

        assertEquals(gameObj.getRounds(), roundList);
    }

    @Test
    @DisplayName("Get rounds of a game")
    public void getRoundsTest() throws NoSuchFieldException, IllegalAccessException {
        Game gameObj = new Game();
        List<Round> roundList = null;
        final Field field = gameObj.getClass().getDeclaredField("rounds");
        field.setAccessible(true);
        field.set(gameObj, roundList);

        List<Round> result = gameObj.getRounds();

        assertEquals(roundList, result);
    }
}
