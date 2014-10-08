package edu.iastate.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Game")
public class Game {

    @Id
    @GeneratedValue
    @Column(name = "game_id")
    private int id;

    @Column(name = "game_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date gameTime;

    @Column(name = "game_location")
    private String gameLocation;

    // private Game nextGame;
    // private List<Team> teams;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    public String getGameLocation() {
        return gameLocation;
    }

    public void setNextGame(Game game) {}

    public void addTeam(Team team) {}
}
