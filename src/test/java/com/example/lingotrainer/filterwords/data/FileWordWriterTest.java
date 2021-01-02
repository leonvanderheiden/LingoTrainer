package com.example.lingotrainer.filterwords.data;

import com.example.lingotrainer.word.data.WordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FileWordWriterTest {

    @Mock
    private WordRepository wordRepository;

    @InjectMocks
    private FileWordWriter fileWordWriter;

    List<String> wordList = new ArrayList<>();

    @BeforeEach
    void init() {
        wordList.clear();
        wordList.add("woord");
        wordList.add("super");
        wordList.add("joviaal");
        wordList.add("goeie");
    }

    @Test
    @DisplayName("write words to database when repository is empty")
    void writeWordsTest() {
        when(wordRepository.count()).thenReturn(0L);

        boolean result = fileWordWriter.writeWords(wordList);

        assertEquals(true, result);
    }

    @Test
    @DisplayName("don't write words to database when repository is not empty")
    void skipWriteWordsTest() {
        when(wordRepository.count()).thenReturn(17000L);

        boolean result = fileWordWriter.writeWords(wordList);

        assertEquals(false, result);
    }
}
