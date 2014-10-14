package edu.iastate.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="player")
public class Player extends Member {
	

    @ManyToMany(mappedBy = "players")
    private List<Team> teams;
    
    @ManyToMany
    @JoinColumn(name = "game_id")
    private List<Game> games;
    
    //@Id
    @JoinColumn(name = "member_id")
    private int id;

    
    @Column(name = "free_agent")
    private Boolean isFreeAgent;
    
    public Boolean isFreeAgent() {
    	return isFreeAgent;
    }
    
    public String getPassword() {
    	return super.getPassword();
    }

}

