package com.example.lingotrainer.filterwords.data;

import com.example.lingotrainer.filterwords.domain.WordWriter;
import com.example.lingotrainer.word.data.WordRepository;
import com.example.lingotrainer.word.domain.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileWordWriter implements WordWriter {

    @Autowired
    WordRepository wordRepository;

    @Autowired
    public FileWordWriter() { }

    @Override
    public boolean writeWords(List<String> validWords) {
        System.out.println(wordRepository.count());
            for (String word : validWords) {
                Word newWord = new Word(word);

            }
        return true;
    }
}
