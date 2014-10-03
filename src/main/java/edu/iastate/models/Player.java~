package edu.iastate.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

public class Player extends Member {
	
    @ManyToMany
    @JoinColumn(name = "team_id")
    private List<Team> teams;
    
    @ManyToMany
    @JoinColumn(name = "game_id")
    private List<Game> games;
    
    @Column(name = "free_agent")
    private Boolean isFreeAgent;
    
    public List<Team> getTeams() {
        return teams;
    }
    
    public List<Game> getGames() {
        return games;
    }
    
    public Boolean isFreeAgent() {
    	return isFreeAgent;
    }

}
