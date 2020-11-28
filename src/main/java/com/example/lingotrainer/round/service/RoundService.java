package com.example.lingotrainer.round.service;

import com.example.lingotrainer.round.Round;
import com.example.lingotrainer.round.repository.RoundRepository;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class RoundService implements RoundServiceInterface {

    private RoundRepository roundRepository;

    public RoundService(RoundRepository roundRepository) {
        this.roundRepository = roundRepository;
    }

    @Override
    public Round getRound(long id) {
        return roundRepository.findByid(id);
    }
}
