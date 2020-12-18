package com.example.lingotrainer.highscore.application;

import com.example.lingotrainer.highscore.data.HighscoreRepository;
import com.example.lingotrainer.highscore.domain.Highscore;
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
public class HighscoreServiceTest {

    @Mock
    private HighscoreRepository highscoreRepository;

    @InjectMocks
    private HighscoreService highscoreService;

    Highscore HIGHSCORE_A = new Highscore(1L, 500L);
    Highscore HIGHSCORE_B = new Highscore(1L, 999L);

    @Test
    @DisplayName("get an existing highscore")
    void findByIdTest() {
        given(highscoreRepository.findById(HIGHSCORE_A.getId())).willReturn(Optional.of(HIGHSCORE_A));

        Highscore expected = highscoreService.findById(HIGHSCORE_A.getId());

        assertEquals(expected, HIGHSCORE_A);
    }

    @Test
    @DisplayName("saving a new highscore")
    void saveTest() {
        when(highscoreRepository.save(HIGHSCORE_A)).thenReturn(HIGHSCORE_A);

        Highscore expected = highscoreService.save(HIGHSCORE_A);

        assertEquals(expected, HIGHSCORE_A);
    }

    @Test
    @DisplayName("updating an existing highscore")
    void updateTest() {
        given(highscoreRepository.findById(HIGHSCORE_A.getId())).willReturn(Optional.of(HIGHSCORE_A));
        when(highscoreRepository.save(HIGHSCORE_A)).thenReturn(HIGHSCORE_B);

        Highscore expected = highscoreService.updateById(1L, HIGHSCORE_A);

        assertEquals(expected, HIGHSCORE_B);
    }

    @Test
    @DisplayName("deleting an existing highscore by id")
    void deleteById() {
        given(highscoreRepository.findById(HIGHSCORE_A.getId())).willReturn(Optional.of(HIGHSCORE_A));

        Boolean expected = highscoreService.deleteById(1L);

        assertEquals(expected, true);
    }
}
