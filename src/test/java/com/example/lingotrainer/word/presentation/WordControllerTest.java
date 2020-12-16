package com.example.lingotrainer.word.presentation;

import com.example.lingotrainer.word.application.WordServiceInterace;
import com.example.lingotrainer.word.domain.Word;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WordController.class)
public class WordControllerTest {

    @MockBean
    private WordServiceInterace wordService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void getRoundByIdTest() throws Exception {
        Word word = new Word("doods");
        word.setId(3539L);

        given(wordService.findById(word.getId())).willReturn(word);

        mvc.perform(get("/word/" + word.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3539L))
                .andExpect(jsonPath("$.word").value("doods"))
                .andDo(MockMvcResultHandlers.print());
    }
}
