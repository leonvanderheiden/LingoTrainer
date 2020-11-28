package com.example.lingotrainer.round.repository;

import com.example.lingotrainer.round.Round;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoundRepository extends JpaRepository<Round, Long> {
    Round findByid(Long id);
}
