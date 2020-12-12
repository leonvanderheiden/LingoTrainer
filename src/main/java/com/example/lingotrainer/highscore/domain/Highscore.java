package com.example.lingotrainer.highscore.domain;

import javax.persistence.*;

@Entity
@Table(name = "highscore")
public class Highscore {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    //@OneToOne(mappedBy = "highscore")
    //private Player player;
}
