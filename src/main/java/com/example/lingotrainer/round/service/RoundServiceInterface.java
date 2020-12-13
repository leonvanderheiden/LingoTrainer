package com.example.lingotrainer.round.service;

import com.example.lingotrainer.round.domain.Round;

public interface RoundServiceInterface {

    Round findById(long id);

    String getFeedback(String attempt, int attemptNum, Round round);

    Round save(Round round);

    Round updateById(Long roundid, Round round);

    Boolean deleteById(Long roundid);

}
