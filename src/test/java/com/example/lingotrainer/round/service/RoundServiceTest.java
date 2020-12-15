package com.example.lingotrainer.round.service;

import com.example.lingotrainer.filterwords.domain.LingoWordFilter;
import com.example.lingotrainer.round.domain.Round;
import com.example.lingotrainer.word.data.WordRepository;
import com.example.lingotrainer.word.domain.Word;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class RoundServiceTest {
    /*private RoundService roundService;

    @Autowired
    private WordRepository wordRepository;

    private static Stream<Arguments> provideWordResult() {
        Word word = new Word("boven");
        Round round = new Round(0L, "5 letterwoord", word);


        return Stream.of(
                Arguments.of("fout", round, "false")
        );
    }

    @ParameterizedTest
    @MethodSource("provideWordResult")
    public void getFeedbackTest(String attempt, Round round, String expectedresult) {
        roundService = new RoundService();

        String result = roundService.getFeedback(attempt, round);

        assertEquals(expectedresult, result);
    }*/
}
