package edu.iastate.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    
    //@ManyToMany(fetch = FetchType.LAZY, mappedBy = "team")
    //private List<Player> players;

    //@ManyToMany(fetch = FetchType.LAZY, mappedBy = "team")
    //private List<Game> games;

    /*private Player teamLeader;

    public Player getTeamLeader() {
		return teamLeader;
	}

	public void setTeamLeader(Player teamLeader) {
		this.teamLeader = teamLeader;
	}*/

	@ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;
	
/*	@ManyToMany
	@JoinColumn(name = "game_id")
	private Game game;*/
	
/*	@ManyToMany
	@JoinColumn(name = "member_id")
	private Player player;*/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAcceptFreeAgents() {
		return acceptFreeAgents;
	}

	public void setAcceptFreeAgents(boolean acceptFreeAgents) {
		this.acceptFreeAgents = acceptFreeAgents;
	}

	/*public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}*/

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Team other = (Team) obj;
        if(id != other.id)
            return false;
        return true;
	}

	@Override
	public int hashCode() {
		//TO-DO ... Review 		
		final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
	}
	
	
    
    
}
