package com.example.lingotrainer.highscore;

import com.example.lingotrainer.player.Player;

import javax.persistence.*;

@Entity
@Table(name = "highscore")
public class Highscore {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "highscore")
    private Player player;
}
