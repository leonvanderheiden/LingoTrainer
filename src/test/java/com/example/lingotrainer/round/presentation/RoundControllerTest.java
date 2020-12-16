package com.example.lingotrainer.round.presentation;

import com.example.lingotrainer.round.application.RoundServiceInterface;
import com.example.lingotrainer.round.domain.Round;
import com.example.lingotrainer.word.application.WordServiceInterace;
import com.example.lingotrainer.word.domain.Word;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.mockito.BDDMockito.given;
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
    public void saveRoundTest() throws Exception {
        /*core score = new Score();
        score.setScore(75L);

        given(roundService.save(score)).willReturn(score);

        mvc.perform( MockMvcRequestBuilders
                .post("/round")
                .content(asJsonString(score))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());*/
    }

    @Test
    public void updateRoundTest() throws Exception
    {
        /*Score newScoreObject = new Score(2L, 100L);

        mvc.perform(MockMvcRequestBuilders
                .put("/score/{id}", 2)
                .content(asJsonString(newScoreObject))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
        //.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2L))
        //.andExpect(MockMvcResultMatchers.jsonPath("$.score").value(100L));*/
    }

    @Test
    public void getRoundByIdTest() throws Exception {
        Round round = new Round();
        round.setRoundType("5 letterwoord");

        Word word = new Word("doods");
        word.setId(3539L);
        round.setWord(word);
        round.setId(65L);

        given(roundService.findById(round.getId())).willReturn(round);

        mvc.perform(get("/round/" + round.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roundType").value("5 letterwoord"))
                .andExpect(jsonPath("$.word.id").value(word.getId()))
                .andExpect(jsonPath("$.word.word").value(word.getWord()))
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
