package com.example.lingotrainer;

import com.example.lingotrainer.filterwords.domain.LingoWordFilter;
import com.example.lingotrainer.round.application.RoundServiceInterface;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.Assert.*;

public class MyUnitTest {

    RoundServiceInterface rs;


    @Test
    public void testConcatenate() {
        MyUnit myUnit = new MyUnit();

        String result = myUnit.concatenate("one", "two");

        assertEquals("onetwo", result);
    }

    private static Stream<Arguments> provideWordResult() {
        return Stream.of(
                Arguments.of("goeie", true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideWordResult")
    public void isWordValid(String word, boolean expectedresult) {
        LingoWordFilter lingoWordFilter = new LingoWordFilter();

        boolean result = lingoWordFilter.isWordValid(word);

        assertEquals(expectedresult, result);
    }

}