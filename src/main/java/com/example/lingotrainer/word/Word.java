package com.example.lingotrainer.word;

import com.example.lingotrainer.round.Round;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "word")
public class Word {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String word;

    @OneToMany(mappedBy = "word")
    private List<Round> rounds;

    public Word() {

    }

    public Word(String word) {
        this.word = word;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }
}
