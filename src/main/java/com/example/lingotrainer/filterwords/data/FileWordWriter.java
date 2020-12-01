package com.example.lingotrainer.filterwords.data;

import com.example.lingotrainer.filterwords.domain.WordWriter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileWordWriter implements WordWriter {

    @Override
    public boolean writeWords(List<String> validWords) {
        for (String word : validWords) {
            System.out.println(word);
        }
        return true;
    }
}
