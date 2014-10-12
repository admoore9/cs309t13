package edu.iastate.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

public class Player extends Member {
	
    @Id
    @JoinColumn(name = "member_id")
    private int id;
    
    @Column(name = "free_agent")
    private Boolean isFreeAgent;
    
    public Boolean isFreeAgent() {
    	return isFreeAgent;
    }

}

