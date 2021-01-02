package com.example.lingotrainer.word.domain;

import com.example.lingotrainer.round.domain.Round;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "word")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Word {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String word;

    @OneToMany(mappedBy = "word")
    private List<Round> rounds;

    public Word() { }

    public Word(String word) {
        this.word = word;
    }

    public Word(Long id, String word) {
        this.id = id;
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
