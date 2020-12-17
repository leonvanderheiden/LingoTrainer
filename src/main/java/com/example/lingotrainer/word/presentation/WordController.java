package com.example.lingotrainer.word.presentation;

import com.example.lingotrainer.word.domain.Word;
import com.example.lingotrainer.word.application.WordServiceInterace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WordController {

    @Autowired
    public WordServiceInterace wordService;

    //Returned een random woord van een bepaalde lengte in de database
    @GetMapping("/getrandomword/{length}")
    public Word getRandomWord(@PathVariable Long length) {
        return wordService.getRandomWordByLength(length);
    }

    @GetMapping("/word/{id}")
    public Word getWordById(@PathVariable Long id) {
        return wordService.findById(id);
    }

    @PostMapping(
            value = "/word",
            consumes = "application/json",
            produces = "application/json")
    public Word saveWord(@RequestBody Word Word) {
        return wordService.save(Word);
    }

    @PutMapping("/word/{wordid}")
    public Word updateWord(@PathVariable Long wordid, @RequestBody Word word) {
        return wordService.updateById(wordid, word);
    }

    @DeleteMapping("/word/{wordid}")
    public Boolean deleteWord(@PathVariable Long wordid) {
        return wordService.deleteById(wordid);
    }
}
