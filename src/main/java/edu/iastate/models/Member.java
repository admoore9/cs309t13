package edu.iastate.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import edu.iastate.models.Admin.View;

/**
 * 
 * @author nawaf
 *
 */

@Entity
@Table(name = "Member")
public class Member {

    
    public Member() {}

    public Member(String name, String username, String password,
            UserType userType) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }
    
    public Member(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.userType = UserType.PLAYER;
    }

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
    
    public enum UserType {PLAYER, OFFICIAL, ADMIN}; 

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "user_type")
    private UserType userType;

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Boolean getIsOfficial() {
        return isOfficial;
    }

    public void setIsOfficial(Boolean isOfficial) {
        this.isOfficial = isOfficial;
    }
    
}

