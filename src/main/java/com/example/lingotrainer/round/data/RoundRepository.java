package com.example.lingotrainer.round.data;

import com.example.lingotrainer.round.domain.Round;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoundRepository extends JpaRepository<Round, Long> {
}
