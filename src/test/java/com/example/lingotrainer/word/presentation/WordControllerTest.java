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

    @Test
    @DisplayName("getting an existing word by id")
    public void getWordByIdTest() throws Exception {
        Word word = new Word("super");
        word.setId(15167L);

        given(wordService.findById(word.getId())).willReturn(word);

        mvc.perform(get("/word/" + word.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(15167L))
                .andExpect(jsonPath("$.word").value("super"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("saving a new word")
    public void saveWordTest() throws Exception {
        Word word = new Word("super");
        word.setId(15167L);

        when(wordService.save(any())).thenReturn(word);

        mvc.perform( MockMvcRequestBuilders
                .post("/word")
                .content(asJsonString(word))
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
        Word oldWord = new Word("super"); oldWord.setId(15167L);
        Word newWord = new Word("toppp"); newWord.setId(15167L);

        given(wordService.updateById(any(), any())).willReturn(newWord);

        mvc.perform(MockMvcRequestBuilders
                .put("/word/{id}", 15167L)
                .content(asJsonString(oldWord))
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
