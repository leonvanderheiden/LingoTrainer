package com.example.lingotrainer.round.presentation;

import com.example.lingotrainer.round.application.RoundServiceInterface;
import com.example.lingotrainer.round.domain.Round;
import com.example.lingotrainer.word.application.WordServiceInterace;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RoundController.class)
public class RoundControllerTest {

    @MockBean
    private RoundServiceInterface roundService;

    @MockBean
    private WordServiceInterace wordService;

    @Autowired
    private MockMvc mvc;

    Word WORD_A = new Word(15167L, "super");
    Word WORD_B = new Word(1L, "topper");
    Round ROUND_A = new Round(1L, "5 letterwoord", WORD_A);
    Round ROUND_B = new Round(2L, "6 letterwoord", WORD_B);

    @Test
    @DisplayName("getting an existing round by id")
    public void getRoundByIdTest() throws Exception {

        given(roundService.findById(ROUND_A.getId())).willReturn(ROUND_A);

        mvc.perform(get("/round/" + ROUND_A.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.roundType").value("5 letterwoord"))
                .andExpect(jsonPath("$.word.id").value(15167L))
                .andExpect(jsonPath("$.word.word").value("super"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("saving a new round")
    public void saveRoundTest() throws Exception {

        when(roundService.save(any())).thenReturn(ROUND_A);

        mvc.perform( MockMvcRequestBuilders
                .post("/round")
                .content(asJsonString(ROUND_A))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.roundType").value("5 letterwoord"))
                .andExpect(jsonPath("$.word.id").value(15167L))
                .andExpect(jsonPath("$.word.word").value("super"));
    }

    @Test
    @DisplayName("updating an existing round")
    public void updateRoundTest() throws Exception
    {
        given(roundService.updateById(any(), any())).willReturn(ROUND_B);

        mvc.perform(MockMvcRequestBuilders
                .put("/round/{id}", 1L)
                .content(asJsonString(ROUND_A))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.roundType").value("6 letterwoord"))
                .andExpect(jsonPath("$.word.id").value(1L))
                .andExpect(jsonPath("$.word.word").value("topper"));
    }

    @Test
    @DisplayName("deleting an existing round by id")
    public void deleteRoundByIdTest() throws Exception {

        given(roundService.deleteById(any())).willReturn(true);

        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .delete("/round/" + ROUND_A.getId())
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
