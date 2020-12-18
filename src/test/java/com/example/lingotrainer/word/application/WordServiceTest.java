package com.example.lingotrainer.word.application;

import com.example.lingotrainer.word.data.WordRepository;
import com.example.lingotrainer.word.domain.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WordServiceTest {

    @Mock
    private WordRepository wordRepository;

    @InjectMocks
    private WordService wordService;

    List<Word> wordList = new ArrayList<>();
    Word WORD_A = new Word(1L, "woord");
    Word WORD_B = new Word(1L, "super");
    Word WORD_C = new Word(2L, "tests");
    Word WORD_D = new Word(3L, "goeie");
    Word WORD_E = new Word(4L, "krijg");

    @BeforeEach
    void init() {
        wordList.clear();
        wordList.add(WORD_A);
        wordList.add(WORD_C);
        wordList.add(WORD_D);
        wordList.add(WORD_E);
    }

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

    @Test
    @DisplayName("deleting an existing word by id")
    void deleteById() {
        given(wordRepository.findById(WORD_A.getId())).willReturn(Optional.of(WORD_A));

        Boolean expected = wordService.deleteById(1L);

        assertEquals(expected, true);
    }

    @Test
    @DisplayName("getting a random word by length")
    void getRandomWordByLengthTest() {
        when(wordRepository.getAllByIdNotNull()).thenReturn(wordList);

        Word randomWord = wordService.getRandomWordByLength(5L);

        assertThat(wordList.contains(randomWord));
    }
}
