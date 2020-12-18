package com.example.lingotrainer.player.application;

import com.example.lingotrainer.game.domain.Game;
import com.example.lingotrainer.highscore.domain.Highscore;
import com.example.lingotrainer.player.application.PlayerService;
import com.example.lingotrainer.player.data.PlayerRepository;
import com.example.lingotrainer.player.domain.Player;
import com.example.lingotrainer.score.domain.Score;
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
public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService;

    Score SCORE_A = new Score(1L, 350L);
    Score SCORE_B = new Score(2L, 400L);
    Game GAME_A = new Game(1L, SCORE_A);
    Game GAME_B = new Game(2L, SCORE_B);
    Highscore HIGHSCORE_A = new Highscore(1L, 500L);
    List<Game> gameList_A = new ArrayList<>();
    List<Game> gameList_B = new ArrayList<>();
    Player PLAYER_A = new Player(1L, HIGHSCORE_A, "test", "password");
    Player PLAYER_B = new Player(1L, HIGHSCORE_A, "test", "password123");

    @BeforeEach
    void init() {
        gameList_A.clear();
        gameList_B.clear();

        gameList_A.add(GAME_A);
        gameList_B.add(GAME_A);
        gameList_B.add(GAME_B);

        PLAYER_A.setGames(gameList_A);
        PLAYER_B.setGames(gameList_B);
    }

    @Test
    @DisplayName("get an existing player")
    void findByIdTest() {
        given(playerRepository.findById(PLAYER_A.getId())).willReturn(Optional.of(PLAYER_A));

        Player expected = playerService.findById(PLAYER_A.getId());

        assertEquals(expected, PLAYER_A);
    }

    @Test
    @DisplayName("saving a new player")
    void saveTest() {
        when(playerRepository.save(PLAYER_A)).thenReturn(PLAYER_A);

        Player expected = playerService.save(PLAYER_A);

        assertEquals(expected, PLAYER_A);
    }

    @Test
    @DisplayName("updating an existing player")
    void updateTest() {
        given(playerRepository.findById(PLAYER_A.getId())).willReturn(Optional.of(PLAYER_A));
        when(playerRepository.save(PLAYER_A)).thenReturn(PLAYER_B);

        Player expected = playerService.updateById(1L, PLAYER_A);

        assertEquals(expected, PLAYER_B);
    }

    @Test
    @DisplayName("deleting an existing player by id")
    void deleteById() {
        given(playerRepository.findById(PLAYER_A.getId())).willReturn(Optional.of(PLAYER_A));

        Boolean expected = playerService.deleteById(1L);

        assertEquals(expected, true);
    }
}
