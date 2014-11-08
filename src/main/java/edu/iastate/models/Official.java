package edu.iastate.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "Official")
public class Official extends Player {

    public Official() {
        super(UserType.OFFICIAL);
    }

    public Official(String name, String username, String password) {
        super(name, username, password,
                UserType.OFFICIAL);
    }
    
    protected Official(UserType userType) {
        super(userType);
    }

    protected Official(String name, String username, String password,
            UserType userType) {
        super(name, username, password, userType);
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "officials")
    private List<Game> games;

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
