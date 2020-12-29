package com.example.lingotrainer.round.application;

import com.example.lingotrainer.round.application.RoundService;
import com.example.lingotrainer.round.data.RoundRepository;
import com.example.lingotrainer.round.domain.Round;
import com.example.lingotrainer.word.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoundServiceTest {

    @Mock
    private RoundRepository roundRepository;

    @InjectMocks
    private RoundService roundService;

    Word WORD_A = new Word(1L, "woord");
    Word WORD_B = new Word(2L, "topper");
    Round ROUND_A = new Round(1L, "5 letterwoord", WORD_A);
    Round ROUND_B = new Round(1L, "6 letterwoord", WORD_B);

    @Test
    @DisplayName("get an existing round")
    void findByIdTest() {
        given(roundRepository.findById(ROUND_A.getId())).willReturn(Optional.of(ROUND_A));

        Round expected = roundService.findById(ROUND_A.getId());

        assertEquals(expected, ROUND_A);
    }

    @Test
    @DisplayName("saving a new round")
    void saveTest() {
        when(roundRepository.save(ROUND_A)).thenReturn(ROUND_A);

        Round expected = roundService.save(ROUND_A);

        assertEquals(expected, ROUND_A);
    }

    private static Stream<Arguments> provideResultForFeedback() {
        return Stream.of(
                Arguments.of("zagen", "dooft", "z (absent)\na (absent)\ng (absent)\ne (absent)\nn (absent)"),
                Arguments.of("dagen", "dooft", "d (correct)\na (absent)\ng (absent)\ne (absent)\nn (absent)"),
                Arguments.of("rooft", "dooft", "r (absent)\no (correct)\no (correct)\nf (correct)\nt (correct)"),

                //Present check
                Arguments.of("daten", "dooft", "d (correct)\na (absent)\nt (present)\ne (absent)\nn (absent)"),
                Arguments.of("oplos", "dooft", "o (present)\np (absent)\nl (absent)\no (present)\ns (absent)"),
                Arguments.of("obool", "dooft", "o (present)\nb (absent)\no (correct)\no (absent)\nl (absent)"),
                Arguments.of("aarde", "ander", "a (correct)\na (absent)\nr (present)\nd (present)\ne (present)"),
                Arguments.of("ander", "aarde", "a (correct)\nn (absent)\nd (present)\ne (present)\nr (present)")
        );
    }

    @ParameterizedTest
    @MethodSource("provideResultForFeedback")
    void generateFeedbackTest(String attempt, String word, String expected) {
        String result = roundService.generateFeedback(attempt, word);

        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideResultForDoubleChar() {
        return Stream.of(
                //1 letter controle controle
                Arguments.of("maart", "ander", 1, true),
                Arguments.of("maart", "ander", 2, false),
                Arguments.of("ander", "maart", 0, true),

                //2 letter controle met GEEN letter op dezelfde positie
                Arguments.of("ordeel", "eerste", 3, true),
                Arguments.of("ordeel", "eerste", 4, true),
                Arguments.of("eerste", "ordeel", 0, true),
                Arguments.of("eerste", "ordeel", 1, true),
                Arguments.of("eerste", "ordeel", 5, false),


                //2 letter controle met een letter op dezelfde positie
                Arguments.of("boord", "donor", 1, true),
                Arguments.of("boord", "donor", 2, true),
                Arguments.of("donor", "boord", 1, true),
                Arguments.of("donor", "boord", 3, true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideResultForDoubleChar")
    void checkDoubleCharsTest(String attempt, String word, int charNum, boolean expected) {
        boolean result = roundService.checkDoubleChars(attempt, word, charNum);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("updating an existing round")
    void updateTest() {
        given(roundRepository.findById(ROUND_A.getId())).willReturn(Optional.of(ROUND_A));
        when(roundRepository.save(ROUND_A)).thenReturn(ROUND_B);

        Round expected = roundService.updateById(1L, ROUND_A);

        assertEquals(expected, ROUND_B);
    }

    @Test
    @DisplayName("deleting an existing round by id")
    void deleteById() {
        given(roundRepository.findById(ROUND_A.getId())).willReturn(Optional.of(ROUND_A));

        Boolean expected = roundService.deleteById(1L);

        assertEquals(expected, true);
    }
}
