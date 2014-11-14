package edu.iastate.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id", unique = true, nullable = false)
    private int member_id;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "sex")
    private String sex;

    @Column(name = "height")
    private Integer height;

    @Column(name = "weight")
    private Integer weight;

    public enum UserType {
        MEMBER, PLAYER, OFFICIAL, COORDINATOR, ADMIN
    };

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "user_type")
    private UserType userType;
    
    public Member() {
        this.userType = UserType.MEMBER;
    }

    public Member(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.userType = UserType.MEMBER;
    }
    
    /**
     * Used only by subclasses to pass user type
     * @param userType
     */
    protected Member(UserType userType) {
        this.userType = userType;
    }

    /**
     * Used only by subclasses to pass user type
     * @param name
     * @param username
     * @param password
     * @param userType
     */

    protected Member(String name, String username, String password,
            UserType userType) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    /**
     * Get user type
     * 
     * @return userType
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * Set user type
     * 
     * @param userType
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    /**
     * Get id
     * 
     * @return
     */
    public int getId() {
        return member_id;
    }

    /**
     * Set id
     * 
     * @param id
     */
    public void setId(int id) {
        this.member_id = id;
    }

    /**
     * Get name
     * 
     * @return
     */
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
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Member other = (Member) obj;
        if(member_id != other.member_id)
            return false;
        return true;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
