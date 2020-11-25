package com.example.lingotrainer.filterwords;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilterWords {
    public List<String> getWords() throws FileNotFoundException {
        List<String> words = new ArrayList<>();

        File allWords = new File("C:\\Users\\Leon\\Documents\\lingotrainer\\src\\main\\resources\\basiswoorden-gekeurd.txt");
        Scanner wordReader = new Scanner(allWords);
        while (wordReader.hasNextLine()) {
            String word = wordReader.nextLine();
            //Alleen non-capital woorden zijn toegestaan die in totaal 5, 6 of 7 letters bevatten
            if (word.matches("[a-z]{5,7}"))
            {
                words.add(word);
            }
        }
        return words;
    }

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
