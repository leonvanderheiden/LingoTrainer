package com.example.lingotrainer.score.data;

import com.example.lingotrainer.score.domain.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, Long> {
}
