package edu.iastate.models;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
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

    public Player() {
        super();
    }

    public Player(String name, String username, String password) {
        super(name, username, password,
                UserType.PLAYER);
    }
}

