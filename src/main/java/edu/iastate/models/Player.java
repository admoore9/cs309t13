package edu.iastate.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * 
 * @author nawaf
 *
 */

@Entity
@Table(name = "Player")
public class Player extends Member {

    public Player() {
        super(UserType.PLAYER);
    }

    public Player(String name, String username, String password) {
        super(name, username, password,
                UserType.PLAYER);
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "players")
    private List<Team> teams;

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
