package com.example.lingotrainer.word.repository;

import com.example.lingotrainer.word.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface WordRepository extends JpaRepository<Word, Long> {
    @Transactional
    Word findDistinctById(Long id);

    @Transactional
    Word findWordByWord(String word);

    @Transactional
    Boolean existsByWord(String word);

    @Transactional
    List<Word> getAllByIdNotNull();
}
