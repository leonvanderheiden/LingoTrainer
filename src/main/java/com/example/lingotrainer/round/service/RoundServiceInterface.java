package com.example.lingotrainer.round.service;

import com.example.lingotrainer.round.Round;

import java.util.List;

public interface RoundServiceInterface {

    Round getRound(long id);

    Round save(Round round);

    Round updateById(Long roundid, Round round);

    Boolean deleteById(Long roundid);

}
