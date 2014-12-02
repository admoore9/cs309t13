package edu.iastate.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;

/**
 * Game class
 *
 * @author Andrew
 *
 */

// TODO game winner and associated methods
@Entity
@Table(name = "Game")
public class Game {

    @Id
    @GeneratedValue
    @Column(name = "game_id")
    private int id;

    @Column(name = "round_number")
    private int roundNumber;

    @Column(name = "game_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date gameTime;

    @Column(name = "game_location")
    private String gameLocation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "next_game_id", referencedColumnName = "game_id")
    private Game nextGame;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @JoinTable(name = "teamgamemapper", joinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "game_id")}, inverseJoinColumns = {@JoinColumn(name = "team_id", referencedColumnName = "team_id")})
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Team> teams;

    @JoinTable(name = "officialgamemapper", joinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "game_id")}, inverseJoinColumns = {@JoinColumn(name = "member_id", referencedColumnName = "member_id")})
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Member> officials;

    @JsonManagedReference
    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    private Set<Score> scores;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "winner", referencedColumnName = "team_id")
    private Team winner;

    public Game() {
        teams = new HashSet<Team>();
        officials = new HashSet<Member>();
        scores = new HashSet<Score>();
    }

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

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

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

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
    
    public Set<Member> getOfficials() {
        return officials;
    }

    public void setOfficials(Set<Member> officials) {
        this.officials = officials;
    }

    public Set<Score> getScores() {
        return scores;
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }

    public Team getWinner() {
        return winner;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
    }

    /**
     * Adds team to game. Does nothing if team is null or Game already has this
     * team
     * 
     * @param team Team to be added, 1 if successful and 0 if game has reached
     *            max teams and -1 if team is null or game already contains team
     */
    public int addTeam(Team team) {
        if(team == null || this.teams.contains(team)) {
            return -1;
        }
        if(this.teams.size() == tournament.getTeamsPerGame()) {
            return 0;
        }
        this.teams.add(team);
        return 1;
    }

    /**
     * Removes team to game. Does nothing if team is null or Game does not have
     * this team
     * 
     * @param team The team to be removed
     */
    public void removeTeam(Team team) {
        if(team == null || !this.teams.contains(team)) {
            return;
        }
        this.teams.remove(team);
    }

    /**
     * Add official to this game. Does nothing if official is null or official
     * already exists in game
     * 
     * @param official The official to be added
     * @return returns 1 if successful, 0 if reached max officials per game and
     *         -1 if official is null or official already exists
     * 
     */
    public int addOfficial(Member official) {
        if(official == null || this.officials.contains(official)  || official.getUserType()!=Member.UserType.OFFICIAL) {
            return -1;
        }
        if(this.officials.size() == tournament.getOfficialsPerGame()) {
            return 0;
        }
        this.officials.add(official);
        return 1;
    }

    /**
     * Remove official from this game. Does nothing if official is null or
     * official does not exist in game
     * 
     * @param official The official to be removed
     */
    public void removeOfficial(Member official) {
        if(official == null || !this.officials.contains(official)) {
            return;
        }
        this.officials.remove(official);
    }

    /**
     * Checks if this game has the required teams per game
     * 
     * @return True if it does have the required games, false other wise
     */
    public boolean hasTeamsPerGame() {
        return this.teams.size() == tournament.getTeamsPerGame();
    }

    /**
     * Checks if this game has the required teams per game
     * 
     * @return True if it does have the required games, false other wise
     */
    public boolean hasOfficialsPerGame() {
        return this.officials.size() == tournament.getOfficialsPerGame();
    }
}
