package com.example.lingotrainer.word.repository;

import com.example.lingotrainer.word.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long> {
    Word findByid(Long id);
}
