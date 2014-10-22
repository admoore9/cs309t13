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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        return true;
    }
}

