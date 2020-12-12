package com.example.lingotrainer.word.data;

import com.example.lingotrainer.word.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface WordRepository extends JpaRepository<Word, Long> {

    @Transactional
    Boolean existsByWord(String word);

    @Transactional
    List<Word> getAllByIdNotNull();
}
