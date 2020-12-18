package com.example.lingotrainer.game.presentation;

import com.example.lingotrainer.game.application.GameServiceInterface;
import com.example.lingotrainer.game.domain.Game;
import com.example.lingotrainer.round.domain.Round;
import com.example.lingotrainer.score.domain.Score;
import com.example.lingotrainer.word.domain.Word;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerTest {
    @MockBean
    private GameServiceInterface gameService;

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("getting an existing game by id")
    public void getGameByIdTest() throws Exception {
        Game game = createGame(500L, 1L, "5 letterwoord", "woord");

        given(gameService.findById(game.getId())).willReturn(game);

        mvc.perform(get("/game/" + game.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.score.id").value(1L))
                .andExpect(jsonPath("$.score.score").value(500L))
                .andExpect(jsonPath("$.rounds[0].id").value(1L))
                .andExpect(jsonPath("$.rounds[0].roundType").value("5 letterwoord"))
                .andExpect(jsonPath("$.rounds[0].word.id").value(1L))
                .andExpect(jsonPath("$.rounds[0].word.word").value("woord"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("saving a new game")
    public void saveGameTest() throws Exception {
        Game game = createGame(500L, 1L, "5 letterwoord", "woord");

        given(gameService.save(any())).willReturn(game);

        mvc.perform( MockMvcRequestBuilders
                .post("/game")
                .content(asJsonString(game))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.score.id").value(1L))
                .andExpect(jsonPath("$.score.score").value(500L))
                .andExpect(jsonPath("$.rounds[0].id").value(1L))
                .andExpect(jsonPath("$.rounds[0].roundType").value("5 letterwoord"))
                .andExpect(jsonPath("$.rounds[0].word.id").value(1L))
                .andExpect(jsonPath("$.rounds[0].word.word").value("woord"));
    }

    @Test
    @DisplayName("updating an existing game")
    public void updateGameTest() throws Exception {
        Game oldGame = createGame(500L, 1L, "5 letterwoord", "woord");
        Game newGame = createGame(500L, 2L, "6 letterwoord", "nieuwe");

        given(gameService.updateById(any(), any())).willReturn(newGame);

        mvc.perform(MockMvcRequestBuilders
                .put("/game/{id}", 1L)
                .content(asJsonString(oldGame))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.score.id").value(1L))
                .andExpect(jsonPath("$.score.score").value(500L))
                .andExpect(jsonPath("$.rounds[0].id").value(2L))
                .andExpect(jsonPath("$.rounds[0].roundType").value("6 letterwoord"))
                .andExpect(jsonPath("$.rounds[0].word.id").value(1L))
                .andExpect(jsonPath("$.rounds[0].word.word").value("nieuwe"));
    }

    public static Game createGame(long score, long roundId, String roundType, String word) {
        Game game = new Game();
        game.setId(1L);

        Score scoreObj = new Score(1L, score);
        List<Round> roundList = new ArrayList<>();

        Round round = new Round();
        round.setId(roundId);
        round.setRoundType(roundType);
        Word wordObj = new Word(word);
        wordObj.setId(1L);
        round.setWord(wordObj);
        roundList.add(round);

        game.setScore(scoreObj);
        game.setRounds(roundList);
        return game;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
