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
	
	/**
	 * Calculates the skill level of the team based on
	 * skill level of players of team
	 */
	public void calculateSkillLevel() {
		int skillLevel = 0;
		int numPlayers = 0;
		for(Player player : players){
			skillLevel+=player.getSkillLevel();
			numPlayers++;
		}
		teamSkillLevel = skillLevel/numPlayers;
	}
	   
	/**
     * Adds a game to the team, does nothing if game has already been
     * added or is null
     * 
     * @param game
     * The game to be added
     */
    public void addGameToTeam(Game game) {
        if(game == null || this.games.contains(game)) {
            return;
        }
        this.games.add(game);
    }
    
    /**
     * Removes a game from the team. Does nothing if game is not in current
     * team's list or game is null
     * 
     * @param game
     * The game to be removed
     */
    public void removeGameFromTeam(Game game) {
        if(game==null || !this.games.contains(game)) {
            return;
        }
        this.games.remove(game);
    }
    
    /**
     * Adds player to this team. Does nothing if player is null or
     * player already exists in current team
     * 
     * @param player
     * The player to be added
     */
    public void addPlayerToTeam(Player player) {
        if(player == null || this.players.contains(player)) {
            return;
        }
        this.players.add(player);
    }
    
    /**
     * Removes player from team. Does nothing if player is null
     * or player does not exist in team
     * 
     * @param player
     * The player to be removed 
     */
    public void removePlayerToTeam(Player player) {
        if(player == null || !this.players.contains(player)) {
            return;
        }
        this.players.remove(player);
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
