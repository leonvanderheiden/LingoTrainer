package com.example.lingotrainer.word.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class WordService implements WordServiceInterace {

    @Autowired
    public WordService() {

    }

    @Override
    public List<String> getWords(long length) throws FileNotFoundException {
        return filterWords(length);
    }

    //Methode om de ongeldige worden uit de textfile te filteren. Ook worden gelijk alle woorden geselecteerd van een bepaalde lengte
    public List<String> filterWords(long length) throws FileNotFoundException {
        List<String> words = new ArrayList<>();

        File allWords = new File("C:\\Users\\Leon\\Documents\\lingotrainer\\src\\main\\resources\\basiswoorden-gekeurd.txt");
        Scanner wordReader = new Scanner(allWords);
        while (wordReader.hasNextLine()) {
            String word = wordReader.nextLine();
            //Alleen non-capital woorden toegestaan van een bepaalde lengte
            if (word.matches("[a-z]{" + length + "}"))
            {
                words.add(word);
            }
        }
        return words;
    }
}
