package com.example.lingotrainer.word;

import com.example.lingotrainer.game.Game;

import javax.persistence.*;

@Entity
@Table(name = "word")
public class Word {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /*@OneToOne(mappedBy = "word")
    private Game game;*/
}
