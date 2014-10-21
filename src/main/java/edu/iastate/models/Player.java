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



import javax.persistence.Table;

/**
 * 
 * @author nawaf
 *
 */

@Entity
@Table(name = "Player")
public class Player extends Member {

    @ManyToMany(mappedBy = "players")
    private List<Team> teams;

    /**
     * A list of surveys completed by player for each tournament
     */
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "player")
//    private List<Survey> surveys;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "player")
//	private List<Badge> badges;
	   
//    public List<Survey> getSurveys() {
//		return surveys;
//	}
//
//	public void setSurveys(List<Survey> surveys) {
//		this.surveys = surveys;
//	}
    
//	public List<Badge> getBadges() {
//	     return badges;
//	}
//
//	public void setBadges(List<Badge> badges) {
//	     this.badges = badges;
//	}

    public Player() {
        super();
    }
    
    public Player(String name, String username, String password) {
        super(name, username, password,
                UserType.PLAYER);
    }
}

