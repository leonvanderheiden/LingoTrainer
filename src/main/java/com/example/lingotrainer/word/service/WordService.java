package com.example.lingotrainer.word.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class WordService implements WordServiceInterace {

    @Autowired
    public WordService() {

    }

    @Override
    public List<String> getWords(long length) throws FileNotFoundException {
        return filterWords();
    }

    //Methode om de ongeldige worden uit de textfile te filteren. Ook worden gelijk alle woorden geselecteerd van een bepaalde lengte
    public List<String> filterWords() throws FileNotFoundException {
        return null;
    }
}
