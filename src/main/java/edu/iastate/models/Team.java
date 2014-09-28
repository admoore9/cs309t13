package edu.iastate.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Team")
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private int id;

    @Column(name = "team_name")
    private String name;

    @Column(name = "accepts_free_agents")
    private boolean acceptFreeAgents;

    // private Player teamLeader;
    // private Set<Player> players;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;
}
