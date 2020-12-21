package com.example.lingotrainer.word.presentation;

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
@WebMvcTest(WordController.class)
public class WordControllerTest {

    @MockBean
    private WordServiceInterace wordService;

    @Autowired
    private MockMvc mvc;

    Word WORD_A = new Word(15167L, "super");
    Word WORD_B = new Word(15167L, "toppp");

    @Test
    @DisplayName("getting an existing word by id")
    public void getWordByIdTest() throws Exception {

        given(wordService.findById(WORD_A.getId())).willReturn(WORD_A);

        mvc.perform(get("/word/" + WORD_A.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(15167L))
                .andExpect(jsonPath("$.word").value("super"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("saving a new word")
    public void saveWordTest() throws Exception {
        when(wordService.save(any())).thenReturn(WORD_A);

        mvc.perform( MockMvcRequestBuilders
                .post("/word")
                .content(asJsonString(WORD_A))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(15167L))
                .andExpect(jsonPath("$.word").value("super"));
    }

    @Test
    @DisplayName("updating an existing word")
    public void updateWordTest() throws Exception
    {
        given(wordService.updateById(any(), any())).willReturn(WORD_B);

        mvc.perform(MockMvcRequestBuilders
                .put("/word/{id}", 15167L)
                .content(asJsonString(WORD_A))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(15167L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.word").value("toppp"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
