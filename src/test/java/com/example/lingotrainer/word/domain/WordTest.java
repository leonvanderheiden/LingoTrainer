package com.example.lingotrainer.word.domain;

import com.example.lingotrainer.round.domain.Round;
import com.example.lingotrainer.word.domain.Word;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordTest {
    @Test
    @DisplayName("Set id of a word object")
    public void setIdTest() {
        long id = 1000;
        Word word = new Word();
        word.setId(id);
        assertEquals(word.getId(), id);
    }

    @Test
    @DisplayName("Get id of a word object")
    public void getIdTest() throws NoSuchFieldException, IllegalAccessException {

        Word word = new Word();
        final Field field = word.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(word, 1L);

        long result = word.getId();

        assertEquals(1L, result);
    }

    @Test
    @DisplayName("Set word of a word object")
    public void setWordTest() {
        String word = "test";
        Word wordObj = new Word();
        wordObj.setWord(word);
        assertEquals(wordObj.getWord(), word);
    }

    @Test
    @DisplayName("Get word of a word object")
    public void getWordTest() throws NoSuchFieldException, IllegalAccessException {

        Word wordObj = new Word();
        final Field field = wordObj.getClass().getDeclaredField("word");
        field.setAccessible(true);
        field.set(wordObj, "test");

        String result = wordObj.getWord();

        assertEquals("test", result);
    }

    @Test
    @DisplayName("Set list of rounds")
    public void setRoundlistTest() {
        List<Round> roundList = null;
        Word wordObj = new Word();

        wordObj.setRounds(roundList);

        assertEquals(wordObj.getWord(), roundList);
    }
}
