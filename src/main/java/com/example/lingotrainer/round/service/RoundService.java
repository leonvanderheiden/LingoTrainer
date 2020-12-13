package com.example.lingotrainer.round.service;

import com.example.lingotrainer.round.domain.Round;
import com.example.lingotrainer.round.data.RoundRepository;
import com.example.lingotrainer.round.exceptions.RoundNotFoundException;
import com.example.lingotrainer.score.domain.Score;
import com.example.lingotrainer.word.data.WordRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RoundService implements RoundServiceInterface {

    @Autowired
    private RoundRepository roundRepository;

    @Autowired
    private WordRepository wordRepository;

    public RoundService(RoundRepository roundRepository) {
        this.roundRepository = roundRepository;
    }

    public RoundService() { }

    @Override
    public Round findById(long roundid) {
        return roundRepository.findById(roundid).orElseThrow(() -> new RoundNotFoundException(roundid));
    }

    @Override
    public String getFeedback(String attempt, int attemptNum, Round round) {


        String feedback = "false";
        String word = round.getWord().getWord();
        int roundType = Character.getNumericValue(round.getRoundType().charAt(0));

        //Controle of het ingevoerde woord bestaat en de woordlengte bij de ronde past
        if (wordRepository.existsByWord(attempt) && attempt.length() == roundType) {
            //Controle of het ingevoerde woord gelijk is aan het ingevoerde woord
            if (word.equals(attempt)) {
                return "true";
            }
            else {
                feedback = "";
                for(int i = 0; i < attempt.length(); i++)
                {
                    char c = attempt.charAt(i);

                    if (c == word.charAt(i)) {
                        //Char is op de juiste plek
                        feedback += c + " (correct)";
                    }
                    else if (word.contains(c + "")) {
                        //Gedeelte van de poging van de user waar de loop tot nu toe doorheen is gegaan
                        String part = attempt.substring(0, i);
                        //Kijkt hoeveel keer de char voor komt in alle letters van de poging die de loop tot nu toe is afgegaan
                        Long n1 = part.chars().filter(num -> num == c).count();
                        //Kijkt hoeveel keer de char voor komt in het te raden woord
                        Long n2 = word.chars().filter(num -> num == c).count();

                        //Als de hoeveelheid van de char in het te raden woord hoger is moet de char nog present zijn ergens
                        if (n2 > n1) {
                            feedback += c + " (present)";
                        }
                        else {
                            feedback += c + " (absent)";
                        }
                    }
                    else {
                        //Char is absent
                        feedback += c + " (absent)";
                    }
                    feedback += "\n";
                }
            }
        }
        return feedback;
    }

    @Override
    public Round save(Round round) {
        return roundRepository.save(round);
    }

    @Override
    public Round updateById(Long roundid, Round round) {
        Round r = roundRepository.findById(roundid).orElseThrow(() -> new RoundNotFoundException(roundid));

        r.setRoundType(round.getRoundType());

        return roundRepository.save(r);
    }

    @Override
    public Boolean deleteById(Long roundid) {
        return roundRepository.deleteDistinctById(roundid);
    }
}
