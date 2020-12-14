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
              Arguments.of("goeie", true)
      );
    }

    @ParameterizedTest
    @MethodSource("provideWordResult")
    public void isWordValidTest(String word, boolean expectedresult) {
        System.out.println("test");
        LingoWordFilter lingoWordFilter = new LingoWordFilter();

        boolean result = lingoWordFilter.isWordValid(word);

        assertEquals(expectedresult, result);
    }
}
