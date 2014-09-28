package edu.iastate.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Tournament class
 * 
 * @author brianshannan
 *
 */

@Entity
@Table(name = "Tournament")
public class Tournament {

    @Id
    @GeneratedValue
    @Column(name = "tournament_id")
    private int id;

    @Column(name = "tournament_name")
    private String name;

    @Column(name = "min_players")
    private int minPlayers;

    @Column(name = "max_players")
    private int maxPlayers;

    @Column(name = "is_double_elimination")
    private boolean isDoubleElimination;

    @Column(name = "is_started")
    private boolean isStarted;

    @OneToMany(mappedBy = "Tournament")
    private List<Team> teams;

    @OneToMany(mappedBy = "Tournament")
    private List<Game> games;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public boolean isDoubleElimination() {
        return isDoubleElimination;
    }

    public void setDoubleElimination(boolean isDoubleElimination) {
        this.isDoubleElimination = isDoubleElimination;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean isStarted) {
        this.isStarted = isStarted;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public String toString() {
        return this.name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Tournament other = (Tournament) obj;
        if(id != other.id)
            return false;
        return true;
    }

}