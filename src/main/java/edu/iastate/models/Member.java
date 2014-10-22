package edu.iastate.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
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

    public enum UserType {PLAYER, OFFICIAL, COORDINATOR, ADMIN}; 

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "user_type")
    private UserType userType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "player")
    private List<Survey> surveys;
    
    public Member() {}

    protected Member(UserType userType) {
        this.userType = userType;
    }
    public Member(String name, String username, String password,
            UserType userType) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public List<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + member_id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Member other = (Member) obj;
        if (member_id != other.member_id)
            return false;
        return true;
    }

}

