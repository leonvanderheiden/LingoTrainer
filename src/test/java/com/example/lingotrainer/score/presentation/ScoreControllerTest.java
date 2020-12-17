package com.example.lingotrainer.score.presentation;

import com.example.lingotrainer.score.application.ScoreServiceInterface;
import com.example.lingotrainer.score.domain.Score;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ScoreController.class)
public class ScoreControllerTest {

    @MockBean
    private ScoreServiceInterface scoreService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void saveScoreTest() throws Exception {
        Score score = new Score(1L, 75L);

        when(scoreService.save(any())).thenReturn(score);

        mvc.perform( MockMvcRequestBuilders
                .post("/score")
                .content(asJsonString(score))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.score").value(75L));
    }

    @Test
    public void updateScoreTest() throws Exception
    {
        Score oldScoreObject = new Score(2L, 50L);
        Score newScoreObject = new Score(2L, 100L);

        given(scoreService.updateById(any(), any())).willReturn(newScoreObject);

        mvc.perform(MockMvcRequestBuilders
                .put("/score/{id}", 2L)
                .content(asJsonString(oldScoreObject))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.score").value(100L));
    }

    @Test
    public void getScoreByIdTest() throws Exception {
        Score score = new Score(216L, 75L);

        given(scoreService.findById(score.getId())).willReturn(score);

        mvc.perform(get("/score/" + score.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.score").value(75l))
                .andDo(MockMvcResultHandlers.print());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
