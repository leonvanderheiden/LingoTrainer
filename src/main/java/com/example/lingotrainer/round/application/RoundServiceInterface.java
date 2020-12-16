package com.example.lingotrainer.round.application;

import com.example.lingotrainer.round.domain.Round;

public interface RoundServiceInterface {

    Round findById(long id);

    String getFeedback(String attempt, Round round);

    Round save(Round round);

    Round updateById(Long roundid, Round round);

    Boolean deleteById(Long roundid);

}
