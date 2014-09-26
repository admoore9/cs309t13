package edu.iastate.models;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Game")
public class Game {

    private Date gameTime;
    private String gameLocation;
    private Game nextGame;
    private Set<Team> teams;
    private Tournament tournament;

    private Map<Team, Integer> scores;
    private Team winner;
}
