package edu.iastate.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Game class
 * 
 * @author Andrew
 *
 */

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

    // @ManyToMany(fetch = FetchType.LAZY, mappedBy = "game")
    // private String nextGame;
    //
    // @ManyToMany(fetch = FetchType.LAZY, mappedBy = "game")
    // private List<Team> teams;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getGameTime() {
        return gameTime;
    }

    public void setGameTime(Date gameTime) {
        this.gameTime = gameTime;
    }

    public String getGameLocation() {
        return gameLocation;
    }

    public void setGameLocation(String gameLocation) {
        this.gameLocation = gameLocation;
    }

    // public String getNextGame() {
    // return nextGame;
    // }
    //
    // public void getNextGame(String nextGame) {
    // this.nextGame = nextGame;
    // }
    //
    // public List<Team> getTeams() {
    // return teams;
    // }
    //
    // public void setTeams(List<Team> teams) {
    // this.teams = teams;
    // }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
}
