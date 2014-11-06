package edu.iastate.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "Official")
public class Official extends Member {

    public Official() {
        super(UserType.OFFICIAL);
    }

    public Official(String name, String username, String password) {
        super(name, username, password,
                UserType.OFFICIAL);
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
