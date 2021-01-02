package com.example.lingotrainer.score.presentation;

import com.example.lingotrainer.score.application.ScoreServiceInterface;
import com.example.lingotrainer.score.domain.Score;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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

    Score SCORE_A = new Score(2L, 50L);
    Score SCORE_B = new Score(2L, 100L);

    @Test
    @DisplayName("getting an existing score by id")
    public void getScoreByIdTest() throws Exception {

        given(scoreService.findById(SCORE_A.getId())).willReturn(SCORE_A);

        mvc.perform(get("/score/" + SCORE_A.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.score").value(50l))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("saving a new score")
    public void saveScoreTest() throws Exception {

        when(scoreService.save(any())).thenReturn(SCORE_A);

        mvc.perform( MockMvcRequestBuilders
                .post("/score")
                .content(asJsonString(SCORE_A))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.score").value(50L));
    }

    @Test
    @DisplayName("updating an existing score")
    public void updateScoreTest() throws Exception
    {
        given(scoreService.updateById(any(), any())).willReturn(SCORE_B);

        mvc.perform(MockMvcRequestBuilders
                .put("/score/{id}", 2L)
                .content(asJsonString(SCORE_A))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.score").value(100L));
    }

    @Test
    @DisplayName("deleting an existing score by id")
    public void deleteScoreByIdTest() throws Exception
    {
        given(scoreService.deleteById(any())).willReturn(true);

        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .delete("/score/" + SCORE_A.getId())
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
