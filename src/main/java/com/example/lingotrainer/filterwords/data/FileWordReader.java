package com.example.lingotrainer.filterwords.data;

import com.example.lingotrainer.filterwords.domain.WordReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class FileWordReader implements WordReader {

    @Value("classpath:basiswoorden-gekeurd.txt")
    private Resource source;

    //Methode om words van een file te lezen
    @Override
    public List<String> getWordsFromFile() throws IOException {
        List<String> words = new ArrayList<>();

        Scanner wordReader = new Scanner(this.source.getFile());
        while (wordReader.hasNextLine()) {
            String word = wordReader.nextLine();
            words.add(word);
        }
        return words;
    }
}
