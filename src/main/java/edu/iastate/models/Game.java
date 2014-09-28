package edu.iastate.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Game")
public class Game {

    @Id
    @GeneratedValue
    @Column(name = "game_id")
    private int id;

    @Column(name = "game_time")
    // TODO
    private Date gameTime;

    @Column(name = "game_location")
    private String gameLocation;

    // @Column(name = "next_game_id")
    // @ManyToOne
    // private Game nextGame;
    // private Set<Team> teams;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    public String getGameLocation() {
        return gameLocation;
    }
}
