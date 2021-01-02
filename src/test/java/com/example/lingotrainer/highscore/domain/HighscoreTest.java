package com.example.lingotrainer.highscore.domain;

import com.example.lingotrainer.highscore.domain.Highscore;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HighscoreTest {
    @Test
    @DisplayName("Set id of a highscore object")
    public void setIdTest() {
        long id = 1000;
        Highscore highscore = new Highscore();
        highscore.setId(id);
        assertEquals(highscore.getId(), id);
    }

    @Test
    @DisplayName("Get id of a highscore object")
    public void getIdTest() throws NoSuchFieldException, IllegalAccessException {

        Highscore highscore = new Highscore();
        final Field field = highscore.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(highscore, 1L);

        long result = highscore.getId();

        assertEquals(1L, result);
    }

    @Test
    @DisplayName("Set highscore of a highscore object")
    public void setHighscoreTest() {
        long highscore = 1000;
        Highscore highscoreObj = new Highscore();
        highscoreObj.setHighscore(highscore);
        assertEquals(highscoreObj.getHighscore(), highscore);
    }

    @Test
    @DisplayName("Get highscore of a highscore object")
    public void getHighscoreTest() throws NoSuchFieldException, IllegalAccessException {

        Highscore highscoreObj = new Highscore();
        final Field field = highscoreObj.getClass().getDeclaredField("highscore");
        field.setAccessible(true);
        field.set(highscoreObj, 100L);

        long result = highscoreObj.getHighscore();

        assertEquals(100L, result);
    }
}
