package com.example.lingotrainer.game.application;

import com.example.lingotrainer.game.data.GameRepository;
import com.example.lingotrainer.game.domain.Game;
import com.example.lingotrainer.round.domain.Round;
import com.example.lingotrainer.score.domain.Score;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameService gameService;

    Score SCORE_A = new Score(1L, 50L);
    Word WORD_A = new Word(1L, "woord");
    Word WORD_B = new Word(2L, "topper");
    Round ROUND_A = new Round(1L, "5 letterwoord", WORD_A);
    Round ROUND_B = new Round(2L, "6 letterwoord", WORD_B);
    List<Round> roundList_A = new ArrayList<>();
    List<Round> roundList_B = new ArrayList<>();
    Game GAME_A = new Game(10L, SCORE_A);
    Game GAME_B = new Game(10L, SCORE_A);

    @BeforeEach
    void init() {
        roundList_A.clear();
        roundList_B.clear();

        roundList_A.add(ROUND_A);
        roundList_B.add(ROUND_A);
        roundList_B.add(ROUND_B);

        GAME_A.setRounds(roundList_A);
        GAME_B.setRounds(roundList_B);
    }

    @Test
    @DisplayName("get an existing game")
    void findByIdTest() {
        given(gameRepository.findById(GAME_A.getId())).willReturn(Optional.of(GAME_A));

        Game expected = gameService.findById(GAME_A.getId());

        assertEquals(expected, GAME_A);
    }

    @Test
    @DisplayName("saving a new game")
    void saveTest() {
        when(gameRepository.save(GAME_A)).thenReturn(GAME_A);

        Game expected = gameService.save(GAME_A);

        assertEquals(expected, GAME_A);
    }

    @Test
    @DisplayName("updating an existing game")
    void updateTest() {
        given(gameRepository.findById(GAME_A.getId())).willReturn(Optional.of(GAME_A));
        when(gameRepository.save(GAME_A)).thenReturn(GAME_B);

        Game expected = gameService.updateById(10L, GAME_A);

        assertEquals(expected, GAME_B);
    }
}
