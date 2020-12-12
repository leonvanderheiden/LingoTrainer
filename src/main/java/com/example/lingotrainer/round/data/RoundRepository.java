package com.example.lingotrainer.round.data;

import com.example.lingotrainer.round.domain.Round;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface RoundRepository extends JpaRepository<Round, Long> {

    @Transactional
    Boolean deleteDistinctById(Long routeid);

    @Transactional
    Round findDistinctById(Long roundid);
}
