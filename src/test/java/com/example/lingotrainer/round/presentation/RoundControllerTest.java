package com.example.lingotrainer.round.presentation;

import com.example.lingotrainer.round.application.RoundServiceInterface;
import com.example.lingotrainer.round.domain.Round;
import com.example.lingotrainer.word.application.WordServiceInterace;
import com.example.lingotrainer.word.domain.Word;
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

    @Test
    public void getRoundByIdTest() throws Exception {
        Round round = createRoundObject(1L, "5 letterwoord", 15167L, "super");

        given(roundService.findById(round.getId())).willReturn(round);

        mvc.perform(get("/round/" + round.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.roundType").value("5 letterwoord"))
                .andExpect(jsonPath("$.word.id").value(15167L))
                .andExpect(jsonPath("$.word.word").value("super"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void saveScoreTest() throws Exception {
        Round round = createRoundObject(1L, "5 letterwoord", 15167L, "super");

        when(roundService.save(any())).thenReturn(round);

        mvc.perform( MockMvcRequestBuilders
                .post("/round")
                .content(asJsonString(round))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.roundType").value("5 letterwoord"))
                .andExpect(jsonPath("$.word.id").value(15167L))
                .andExpect(jsonPath("$.word.word").value("super"));
    }

    @Test
    public void updateWordTest() throws Exception
    {
        Round oldRound = createRoundObject(1L, "5 letterwoord", 15167L, "super");
        Round newRound = createRoundObject(1L, "6 letterwoord", 1L, "topper");

        given(roundService.updateById(any(), any())).willReturn(newRound);

        mvc.perform(MockMvcRequestBuilders
                .put("/round/{id}", 1L)
                .content(asJsonString(oldRound))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.roundType").value("6 letterwoord"))
                .andExpect(jsonPath("$.word.id").value(1L))
                .andExpect(jsonPath("$.word.word").value("topper"));
    }

    public static Round createRoundObject(Long roundId, String roundType, Long wordId, String word) {
        Round round = new Round();
        round.setRoundType(roundType);
        round.setId(roundId);

        Word wordObj = new Word(word);
        wordObj.setId(wordId);
        round.setWord(wordObj);
        return round;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
