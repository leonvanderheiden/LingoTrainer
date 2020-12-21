package com.example.lingotrainer.game.presentation;

import com.example.lingotrainer.game.application.GameServiceInterface;
import com.example.lingotrainer.game.domain.Game;
import com.example.lingotrainer.highscore.domain.Highscore;
import com.example.lingotrainer.player.domain.Player;
import com.example.lingotrainer.round.domain.Round;
import com.example.lingotrainer.score.domain.Score;
import com.example.lingotrainer.word.domain.Word;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.*;
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

    Score SCORE_A = new Score(1L, 500L);
    Word WORD_A = new Word(1L, "woord");
    Word WORD_B = new Word(2L, "topper");
    Round ROUND_A = new Round(1L, "5 letterwoord", WORD_A);
    Round ROUND_B = new Round(2L, "6 letterwoord", WORD_B);
    List<Round> roundList_A = new ArrayList<>();
    List<Round> roundList_B = new ArrayList<>();
    Game GAME_A = new Game(1L, SCORE_A, roundList_A);
    Game GAME_B = new Game(1L, SCORE_A);
    Player PLAYER_A = new Player(1L, new Highscore(1L, 500L), "test", "password");

    @Before
    public void init() {
        roundList_A.add(ROUND_A);
        roundList_B.add(ROUND_A);

        roundList_B.add(ROUND_B);

        GAME_A.setRounds(roundList_A);
        GAME_B.setRounds(roundList_B);
    }

    @Test
    @DisplayName("getting an existing game by id")
    public void getGameByIdTest() throws Exception {

        given(gameService.findById(GAME_A.getId())).willReturn(GAME_A);

        mvc.perform(get("/game/" + GAME_A.getId())
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

        given(gameService.save(any())).willReturn(GAME_A);

        mvc.perform( MockMvcRequestBuilders
                .post("/game")
                .content(asJsonString(GAME_B))
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

        given(gameService.updateById(any(), any())).willReturn(GAME_B);

        mvc.perform(MockMvcRequestBuilders
                .put("/game/{id}", 1L)
                .content(asJsonString(GAME_A))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.score.id").value(1L))
                .andExpect(jsonPath("$.score.score").value(500L))
                .andExpect(jsonPath("$.rounds[0].id").value(1L))
                .andExpect(jsonPath("$.rounds[0].roundType").value("5 letterwoord"))
                .andExpect(jsonPath("$.rounds[0].word.id").value(1L))
                .andExpect(jsonPath("$.rounds[0].word.word").value("woord"))
                .andExpect(jsonPath("$.rounds[1].id").value(2L))
                .andExpect(jsonPath("$.rounds[1].roundType").value("6 letterwoord"))
                .andExpect(jsonPath("$.rounds[1].word.id").value(2L))
                .andExpect(jsonPath("$.rounds[1].word.word").value("topper"));
    }

    @Test
    @DisplayName("deleting an existing game by id")
    public void deleteGameByIdTest() throws Exception {

        given(gameService.deleteById(any())).willReturn(true);

        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .delete("/game/" + GAME_A.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        boolean content = Boolean.parseBoolean(result.getResponse().getContentAsString());
        assertThat(content == true);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
