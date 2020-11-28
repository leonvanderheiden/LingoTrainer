package com.example.lingotrainer.highscore.repository;

import com.example.lingotrainer.highscore.Highscore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HighscoreRepository extends JpaRepository<Highscore, Long> {
    Highscore findByid(Long id);
}
