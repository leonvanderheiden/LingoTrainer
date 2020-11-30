package com.example.lingotrainer.filterWords;

import com.example.lingotrainer.filterwords.FilterWordsProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

@SpringBootTest
public class FilterWordsTest {
    @ParameterizedTest
    @MethodSource("wordExamples")
    @DisplayName("Check if word is vallid")
    void filterWords(String word, boolean expectedResult) {
        var filterWords = new FilterWordsProcessor();

        var expectedWord = filterWords.isWordValid(word);

        assertEquals(expectedWord, expectedResult);
    }

    static Stream<Arguments> wordExamples() {
        return Stream.of(
                Arguments.of("goeie", true),
                Arguments.of("fout", false),
                Arguments.of("Foute", false),
                Arguments.of("fout,", false),
                Arguments.of("fout2", false),

                Arguments.of("mooier", true),
                Arguments.of("Fouttt,", false),
                Arguments.of("foutt,", false),
                Arguments.of("foutt2", false),

                Arguments.of("joviaal", true),
                Arguments.of("Foutttt", false),
                Arguments.of("fouttt,", false),
                Arguments.of("fouttt2", false),
                Arguments.of("achtchar", false)

        );

    }
}
