package com.example.lingotrainer.filterwords.data;

import com.example.lingotrainer.filterwords.domain.WordReader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class FileWordReader implements WordReader {

    @Override
    public List<String> getWordsFromFile() throws FileNotFoundException {
        List<String> words = new ArrayList<>();

        //Haalt alle woorden op uit de txt file
        File allWords = new File("C:\\Users\\leonv\\Documents\\JavaProjects\\LingoTrainer\\src\\main\\resources\\basiswoorden-gekeurd.txt");
        Scanner wordReader = new Scanner(allWords);
        while (wordReader.hasNextLine()) {
            String word = wordReader.nextLine();
            words.add(word);
        }
        return words;
    }
}
