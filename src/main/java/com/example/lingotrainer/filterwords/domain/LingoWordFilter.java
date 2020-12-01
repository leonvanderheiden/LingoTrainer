package com.example.lingotrainer.filterwords.domain;

import com.example.lingotrainer.filterwords.domain.WordFilter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LingoWordFilter implements WordFilter {

    @Override
    public List<String> getFilteredWords(List<String> AllWords) {
        List<String> validWords = new ArrayList<>();
        for (String word : AllWords) {
            if (isWordValid(word) == true) {
                validWords.add(word);
            }
        }
        return validWords;
    }

    @Override
    public boolean isWordValid(String word) {
        boolean result = false;
        //Alleen non-capital woorden zijn toegestaan die in totaal 5, 6 of 7 letters bevatten
        if (word.matches("[a-z]{5,7}"))
        {
            result = true;
        }
        return result;
    }
}
