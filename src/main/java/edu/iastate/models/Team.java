package edu.iastate.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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

    @JoinTable(name = "teamplayermapper", joinColumns={@JoinColumn(name = "team_id", referencedColumnName = "team_id")}, 
            inverseJoinColumns={ @JoinColumn(name = "member_id", referencedColumnName = "member_id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Player> players;

    @JoinTable(name="teamgamemapper", joinColumns={@JoinColumn(name = "team_id", referencedColumnName = "team_id")}, 
            inverseJoinColumns={ @JoinColumn(name = "game_id", referencedColumnName = "game_id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Game> games;
    
    @OneToOne
    @JoinColumn(name = "member_id")
    private Player teamLeader;

	@ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;
	
	@Column(name = "team_skill")
	private int teamSkillLevel; 
	
	public Team() {
		
	}
	
	public Team(int id, String name, boolean acceptFreeAgents, List<Player> players, 
						List<Game> games, Player teamLeader){
		this.id = id;
		this.name = name;
		this.acceptFreeAgents = acceptFreeAgents;
		this.players = players;
		this.games = games;
		this.teamLeader = teamLeader;
	}
	
	public Player getTeamLeader() {
		return teamLeader;
	}

	public void setTeamLeader(Player teamLeader) {
		this.teamLeader = teamLeader;
	}

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

	public List<Player> getPlayers() {
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
	}

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}
	
	public int getTeamSkillLevel() {
		calculateSkillLevel();
		return teamSkillLevel;
	}
	
	public void calculateSkillLevel() {
		int skillLevel = 0;
		int numPlayers = 0;
		for(Player player : players){
			skillLevel+=player.getSkillLevel();
			numPlayers++;
		}
		teamSkillLevel = skillLevel/numPlayers;
	}

	public void setTeamSkillLevel(int teamSkillLevel) {
		this.teamSkillLevel = teamSkillLevel;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
}
