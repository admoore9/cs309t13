package edu.iastate.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author nawaf
 *
 */

@Entity
@Table(name = "Member")
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private int id;    
    
    @Column(name = "member_name")
    private String name;    

    @Column(name = "username")
    private String username; 
    
    @Column(name = "password")
    private String password; 
    
    @Column(name = "is_admin")
    private Boolean isAdmin;
    
    @Column(name = "is_official")
    private Boolean isOfficial;
    
    public int getID() {
    	return id;
    }
    
    public String getName() {
    	return name;
    }
    
    public Boolean isAdmin() {
    	return isAdmin;
    }
    
    public Boolean isOfficial() {
    	return isOfficial;
    }
    
}

