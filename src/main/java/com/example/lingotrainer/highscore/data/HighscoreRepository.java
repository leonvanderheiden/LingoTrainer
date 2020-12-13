package com.example.lingotrainer.highscore.data;

import com.example.lingotrainer.highscore.domain.Highscore;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface HighscoreRepository extends JpaRepository<Highscore, Long> {
    @Transactional
    Boolean deleteDistinctById(Long highscoreid);
}
