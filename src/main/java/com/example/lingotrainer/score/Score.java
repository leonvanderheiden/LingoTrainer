package com.example.lingotrainer.score;

import com.example.lingotrainer.game.Game;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "score")
public class Score {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "score")
    private Game game;

    @NotNull
    private long score;

}
