package edu.iastate.models;

import java.util.List;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

public class Official extends Member {
	
    @Id
    @JoinColumn(name = "member_id")
    private int id;
    
    @OneToMany
    @JoinColumn(name = "game_id")
    private List<Game> games;
    
    public List<Game> getGames() {
        return games;
    }
}

