package com.example.lingotrainer.highscore.data;

import com.example.lingotrainer.highscore.domain.Highscore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HighscoreRepository extends JpaRepository<Highscore, Long> {
}