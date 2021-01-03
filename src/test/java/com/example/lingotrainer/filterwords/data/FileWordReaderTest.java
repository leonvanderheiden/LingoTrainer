package com.example.lingotrainer.filterwords.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FileWordReaderTest {

    @Mock
    private Resource source;

    @InjectMocks
    private FileWordReader fileWordReader;

    @Test
    @DisplayName("reading words from file")
    void getWordsFromFileTest() throws IOException {
        File file = new File("src/main/resources/basiswoorden-gekeurd.txt");
        when(source.getFile()).thenReturn(file);

        List<String> words = fileWordReader.getWordsFromFile();

        assertThat(words).isNotNull();
    }
}
