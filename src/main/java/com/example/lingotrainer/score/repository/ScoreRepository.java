package com.example.lingotrainer.score.repository;

import com.example.lingotrainer.round.Round;
import com.example.lingotrainer.score.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    Score findByid(Long id);

    @Transactional
    Boolean deleteDistinctById(Long scoreid);

    @Transactional
    Score findDistinctById(Long scoreid);
}
