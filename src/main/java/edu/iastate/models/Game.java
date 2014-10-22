package edu.iastate.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Game class
 *
 * @author Andrew
 *
 */

@Entity
@Table(name = "Game")
public class Game {

    @Id
    @GeneratedValue
    @Column(name = "game_id")
    private int id;

    @Column(name = "game_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date gameTime;

    @Column(name = "game_location")
    private String gameLocation;

    @ManyToOne(fetch  = FetchType.LAZY)
    @JoinColumn(name = "next_game_id", referencedColumnName = "game_id")
    private Game nextGame;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @ManyToMany(mappedBy = "games")
    private List<Team> teams;

    @JoinTable(name = "officialgamemapper", joinColumns = { @JoinColumn(name = "game_id", referencedColumnName = "game_id") }, inverseJoinColumns = { @JoinColumn(name = "member_id", referencedColumnName = "member_id") })
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Official> officials;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getGameTime() {
        return gameTime;
    }

    public void setGameTime(Date gameTime) {
        this.gameTime = gameTime;
    }

    public String getGameLocation() {
        return gameLocation;
    }

    public Game getNextGame() {
        return nextGame;
    }

    public void setNextGame(Game nextGame) {
        this.nextGame = nextGame;
    }

    public void addTeam(Team team) {}

    // TODO implememnt
    public int getRoundNumber() {
        return -1;
    }

    public void setRoundNumber(int roundNumber) {}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Game other = (Game) obj;
        if(id != other.id)
            return false;
        return true;
    }

    public void setGameLocation(String gameLocation) {
        this.gameLocation = gameLocation;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
    
    /**
     * Adds team to game. Does nothing if team is null or
     * Game already has this team
     * 
     * @param team
     * Team to be added
     */
    public void addTeamToGame(Team team) {
        if(team == null || this.teams.contains(team)) {
            return;
        }
        this.teams.add(team);
    }
    
    /**
     * Removes team to game. Does nothing if team is null or
     * Game does not have this team
     * 
     * @param team
     * The team to be removed
     */
    public void removeTeamFromGame(Team team) {
        if(team == null || !this.teams.contains(team)) {
            return;
        }
        this.teams.remove(team);
    }
    
    /**
     * Add official to this game. Does nothing if official is null or
     * official already exists in game
     * 
     * @param official
     * The official to be added 
     */
    public void addOfficialToGame(Official official) {
        if(official == null || this.officials.contains(official)) {
            return;
        }
        this.officials.add(official);
    }
    
    /**
     * Remove official from this game. Does nothing if official is null or
     * official does not exist in game
     * 
     * @param official
     * The official to be removed 
     */
    public void removeOfficialFromGame(Official official) {
        if(official == null || this.officials.contains(official)) {
            return;
        }
        this.officials.remove(official);
    }
}
