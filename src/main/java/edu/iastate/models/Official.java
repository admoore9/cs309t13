package edu.iastate.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Official")
public class Official extends Member {
	
    @OneToMany
    @JoinColumn(name = "game_id")
    private List<Game> games;
    
    public List<Game> getGames() {
        return games;
    }
}

