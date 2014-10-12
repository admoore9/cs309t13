package edu.iastate.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author nawaf
 *
 */

@Entity
@Table(name = "Player")
public class Player extends Member {
	
    @JoinColumn(name = "member_id")
    private int id;
    
    @Column(name = "free_agent")
    private Boolean freeAgent;
    
    /**
     * A list of surveys completed by player for each game
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
	    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFreeAgent(Boolean freeAgent) {
		this.freeAgent = freeAgent;
	}
    
    public Boolean getFreeAgent() {
    	return freeAgent;
    }
    
    

}

