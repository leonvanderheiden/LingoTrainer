package com.example.lingotrainer.round.domain;

import com.example.lingotrainer.round.domain.Round;
import com.example.lingotrainer.word.domain.Word;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoundTest {
    @Test
    @DisplayName("Set id of a round object")
    public void setIdTest() {
        long id = 1000;
        Round round = new Round();
        round.setId(id);
        assertEquals(round.getId(), id);
    }

    @Test
    @DisplayName("Get id of a round object")
    public void getIdTest() throws NoSuchFieldException, IllegalAccessException {

        Round round = new Round();
        final Field field = round.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(round, 1L);

        long result = round.getId();

        assertEquals(1L, result);
    }

    @Test
    @DisplayName("Set roundtype of a round object")
    public void setRoundTypeTest() {
        String roundType = "5 letterwoord";
        Round roundObj = new Round();
        roundObj.setRoundType(roundType);
        assertEquals(roundObj.getRoundType(), roundType);
    }

    @Test
    @DisplayName("Get roundtype of a round object")
    public void getRoundTypeTest() throws NoSuchFieldException, IllegalAccessException {
        Round roundObj = new Round();
        final Field field = roundObj.getClass().getDeclaredField("roundType");
        field.setAccessible(true);
        field.set(roundObj, "5 letterwoord");

        String result = roundObj.getRoundType();

        assertEquals("5 letterwoord", result);
    }

    @Test
    @DisplayName("Set word of a round")
    public void setWordTest() {
        Word word = new Word(1L, "test");
        Round roundObj = new Round();

        roundObj.setWord(word);

        assertEquals(roundObj.getWord(), word);
    }

    @Test
    @DisplayName("Get word of a round")
    public void getWordTest() throws NoSuchFieldException, IllegalAccessException {
        Round roundObj = new Round();
        Word word = new Word(1L, "test");
        final Field field = roundObj.getClass().getDeclaredField("word");
        field.setAccessible(true);
        field.set(roundObj, word);

        Word result = roundObj.getWord();

        assertEquals(word, result);
    }
}
