package com.example.lingotrainer.highscore.presentation;

import com.example.lingotrainer.highscore.application.HighscoreServiceInterface;
import com.example.lingotrainer.highscore.domain.Highscore;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HighscoreController.class)

public class HighscoreControllerTest {
    @MockBean
    private HighscoreServiceInterface highscoreService;

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("getting an existing highscore by id")
    public void getHighscoreByIdTest() throws Exception {
        Highscore highscore = new Highscore();
        highscore.setId(1L);
        highscore.setHighscore(500L);

        given(highscoreService.findById(highscore.getId())).willReturn(highscore);

        ResultActions rs = mvc.perform(get("/highscore/" + highscore.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.highscore").value(500L))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("saving a new highscore")
    public void saveHighScoreTest() throws Exception {
        Highscore highscore = new Highscore();
        highscore.setId(1L);
        highscore.setHighscore(500L);

        when(highscoreService.save(any())).thenReturn(highscore);

        mvc.perform( MockMvcRequestBuilders
                .post("/highscore")
                .content(asJsonString(highscore))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.highscore").value(500L));
    }

    @Test
    @DisplayName("updating an existing highscore")
    public void updateHighscoreTest() throws Exception
    {
        Highscore oldHighscore = new Highscore();
        oldHighscore.setId(1L); oldHighscore.setHighscore(500L);
        Highscore newHighscore = new Highscore();
        newHighscore.setId(1L); newHighscore.setHighscore(1000L);

        given(highscoreService.updateById(any(), any())).willReturn(newHighscore);

        mvc.perform(MockMvcRequestBuilders
                .put("/highscore/{id}", 1L)
                .content(asJsonString(oldHighscore))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.highscore").value(1000L));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
