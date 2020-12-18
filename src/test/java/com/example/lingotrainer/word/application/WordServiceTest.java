package com.example.lingotrainer.word.application;

import com.example.lingotrainer.word.data.WordRepository;
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
public class WordServiceTest {

    @Mock
    private WordRepository wordRepository;

    @InjectMocks
    private WordService wordService;

    Word WORD_A = new Word(1L, "woord");
    Word WORD_B = new Word(1L, "super");

    @Test
    @DisplayName("get an existing word")
    void findByIdTest() {
        given(wordRepository.findById(WORD_A.getId())).willReturn(Optional.of(WORD_A));

        Word expected = wordService.findById(WORD_A.getId());

        assertEquals(expected, WORD_A);
    }

    @Test
    @DisplayName("saving a new word")
    void saveTest() {
        when(wordRepository.save(WORD_A)).thenReturn(WORD_A);

        Word expected = wordService.save(WORD_A);

        assertEquals(expected, WORD_A);
    }

    @Test
    @DisplayName("updating an existing word")
    void updateTest() {
        given(wordRepository.findById(WORD_A.getId())).willReturn(Optional.of(WORD_A));
        when(wordRepository.save(WORD_A)).thenReturn(WORD_B);

        Word expected = wordService.updateById(1L, WORD_A);

        assertEquals(expected, WORD_B);
    }
}
