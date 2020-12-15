package com.example.lingotrainer.filterwords.domain;

import org.junit.Before;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class LingoWordFilterTest {

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

    @ParameterizedTest
    @MethodSource("provideWordResult")
    public void isWordValidTest(String word, boolean expectedresult) {
        LingoWordFilter lingoWordFilter = new LingoWordFilter();

        boolean result = lingoWordFilter.isWordValid(word);

        assertEquals(expectedresult, result);
    }
}
