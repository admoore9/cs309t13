package edu.iastate.models;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author nawaf
 *
 */

@Entity
@Table(name = "Player")
public class Player extends Member {

    public Player() {
        super(UserType.PLAYER);
    }

    public Player(String name, String username, String password) {
        super(name, username, password,
                UserType.PLAYER);
    }

    protected Player(UserType userType) {
        super(userType);
    }

    protected Player(String name, String username, String password,
            UserType userType) {
        super(name, username, password, userType);
    }

    @ManyToMany(mappedBy = "players")
    private List<Team> teams;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "player")
    private Availability availability;

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    /**
     * @return the availability
     */
    public Availability getAvailability() {
        return availability;
    }

}

