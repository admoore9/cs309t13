package edu.iastate.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


/**
 * 
 * @author nawaf
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name = "Member")
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private int member_id;  

    @Column(name = "name")
    private String name;    

    @Column(name = "username")
    private String username; 

    @Column(name = "password")
    private String password; 

    public enum UserType {PLAYER, OFFICIAL, ADMIN}; 

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "user_type")
    private UserType userType;

    public Member() {}

    public Member(String name, String username, String password,
            UserType userType) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public int getId() {
        return member_id;
    }

    public void setId(int id) {
        this.member_id = id;
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
}

