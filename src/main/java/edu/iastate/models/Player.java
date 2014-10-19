package edu.iastate.models;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author nawaf
 *
 */

@Entity
@Table(name = "Player")
public class Player extends Member {
	
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

