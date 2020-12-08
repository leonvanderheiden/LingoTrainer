package com.example.lingotrainer.round.service;

import com.example.lingotrainer.round.Round;

import java.util.List;

public interface RoundServiceInterface {

    Round getRound(long id);

    String getFeedback(String attempt, Round round);

    Round save(Round round);

    Round updateById(Long roundid, Round round);

    Boolean deleteById(Long roundid);

}
