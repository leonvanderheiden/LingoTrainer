package com.example.lingotrainer.round.application;

import com.example.lingotrainer.round.application.RoundService;
import com.example.lingotrainer.round.data.RoundRepository;
import com.example.lingotrainer.round.domain.Round;
import com.example.lingotrainer.word.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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
