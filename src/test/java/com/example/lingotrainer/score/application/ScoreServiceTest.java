package com.example.lingotrainer.score.application;

import com.example.lingotrainer.score.data.ScoreRepository;
import com.example.lingotrainer.score.domain.Score;
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
public class ScoreServiceTest {

    @Mock
    private ScoreRepository scoreRepository;

    @InjectMocks
    private ScoreService scoreService;

    Score SCORE_A = new Score(10L, 40L);
    Score SCORE_B = new Score(10L, 100L);

    @Test
    @DisplayName("get an existing score")
    void findByIdTest() {
        given(scoreRepository.findById(SCORE_A.getId())).willReturn(Optional.of(SCORE_A));

        Score expected = scoreService.findById(SCORE_A.getId());

        assertEquals(expected, SCORE_A);
    }

    @Test
    @DisplayName("saving a new score")
    void saveTest() {
        when(scoreRepository.save(SCORE_A)).thenReturn(SCORE_A);

        Score expected = scoreService.save(SCORE_A);

        assertEquals(expected, SCORE_A);
    }

    @Test
    @DisplayName("updating an existing score")
    void updateTest() {
        given(scoreRepository.findById(SCORE_A.getId())).willReturn(Optional.of(SCORE_A));
        when(scoreRepository.save(SCORE_A)).thenReturn(SCORE_B);

        Score expected = scoreService.updateById(10L, SCORE_A);

        assertEquals(expected, SCORE_B);
    }

    @Test
    @DisplayName("deleting an existing score by id")
    void deleteById() {
        given(scoreRepository.findById(SCORE_A.getId())).willReturn(Optional.of(SCORE_A));

        Boolean expected = scoreService.deleteById(10L);

        assertEquals(expected, true);
    }
}
