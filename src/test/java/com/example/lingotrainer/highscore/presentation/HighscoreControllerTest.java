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

    Highscore HIGHSCORE_A = new Highscore(1L, 500L);
    Highscore HIGHSCORE_B = new Highscore(1L, 1000L);

    @Test
    @DisplayName("getting an existing highscore by id")
    public void getHighscoreByIdTest() throws Exception {

        given(highscoreService.findById(HIGHSCORE_A.getId())).willReturn(HIGHSCORE_A);

        ResultActions rs = mvc.perform(get("/highscore/" + HIGHSCORE_A.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.highscore").value(500L))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("saving a new highscore")
    public void saveHighScoreTest() throws Exception {

        when(highscoreService.save(any())).thenReturn(HIGHSCORE_A);

        mvc.perform( MockMvcRequestBuilders
                .post("/highscore")
                .content(asJsonString(HIGHSCORE_A))
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
        given(highscoreService.updateById(any(), any())).willReturn(HIGHSCORE_B);

        mvc.perform(MockMvcRequestBuilders
                .put("/highscore/{id}", 1L)
                .content(asJsonString(HIGHSCORE_A))
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
