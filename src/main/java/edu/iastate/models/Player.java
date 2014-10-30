package edu.iastate.models;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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

    @ManyToMany(mappedBy = "players")
    private List<Team> teams;
    
    @ManyToMany(mappedBy = "invitedPlayers")
    private List<Team> invitedTeams;

    public List<Team> getInvitedTeams() {
        return invitedTeams;
    }

    public void setInvitedTeams(List<Team> invitedTeams) {
        this.invitedTeams = invitedTeams;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}

