package com.example.lingotrainer.filterwords.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;

public class LingoWordFilterTest {

    private List<String> wordList = new ArrayList<>();

    private static Stream<Arguments> provideWordResult() {
      return Stream.of(
              Arguments.of("goeie", true),
              Arguments.of("topper", true),
              Arguments.of("joviaal", true),
              Arguments.of("fout", false),
              Arguments.of("achtchar", false),
              Arguments.of("Slecht", false),
              Arguments.of("f0ut3", false),
              Arguments.of("fout-", false),
              Arguments.of("slëcht", false)
      );
    }

    @BeforeEach
    void init() {
        wordList.clear();
        wordList.add("goeie");
        wordList.add("sl3cht");
        wordList.add("topper");
        wordList.add("Slecht");
        wordList.add("joviaal");
        wordList.add("fout");
    }

    @ParameterizedTest
    @MethodSource("provideWordResult")
    public void isWordValidTest(String word, boolean expectedresult) {
        LingoWordFilter lingoWordFilter = new LingoWordFilter();

        boolean result = lingoWordFilter.isWordValid(word);

        assertEquals(expectedresult, result);
    }

    @Test
    @DisplayName("filter valid words from a wordlist")
    public void getFilteredWordsTest() {
        List<String> filteredWords;
        LingoWordFilter lingoWordFilter = new LingoWordFilter();

        filteredWords = lingoWordFilter.getFilteredWords(wordList);

        assertThat(filteredWords.size() == 3);
    }

}
