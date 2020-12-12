package com.example.lingotrainer.round.domain;


import com.example.lingotrainer.word.domain.Word;
import com.sun.istack.NotNull;

import javax.persistence.GenerationType;
import javax.persistence.*;

@Entity
@Table(name = "round")
public class Round {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    String roundType;

    @ManyToOne(fetch = FetchType.LAZY, optional = true, cascade=CascadeType.MERGE)
    @JoinColumn(name = "word_id", nullable = true)
    private Word word;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoundType() {
        return roundType;
    }

    public void setRoundType(String roundType) {
        this.roundType = roundType;
    }

    public Word getWord() { return word; }

    public void setWord(Word word) { this.word = word; }

    @Override
    public String toString() {
        return "Round{" +
                "id=" + id +
                ", roundType='" + roundType + '\'' +
                '}';
    }
}
