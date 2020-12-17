package com.example.lingotrainer.highscore.presentation;

import com.example.lingotrainer.highscore.application.HighscoreServiceInterface;
import com.example.lingotrainer.highscore.domain.Highscore;
import com.example.lingotrainer.word.application.WordServiceInterace;
import com.example.lingotrainer.word.domain.Word;
import com.example.lingotrainer.word.presentation.WordController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.mockito.BDDMockito.given;
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
}
