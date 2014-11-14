package edu.iastate.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;

/**
 * 
 * @author nawaf
 *
 */

@Entity
@Table(name = "Player")
public class Player extends Member {

    public Player() {
        super(UserType.PLAYER);
        this.surveys = new HashSet<Survey>();
    }

    public Player(String name, String username, String password) {
        super(name, username, password,
                UserType.PLAYER);
    }

    protected Player(UserType userType) {
        super(userType);
    }

    protected Player(String name, String username, String password,
            UserType userType) {
        super(name, username, password, userType);
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "players", fetch = FetchType.EAGER)
    private List<Team> teams;

    @JsonIgnore
    @ManyToMany(mappedBy = "invitedPlayers")
    private List<Team> invitedTeams;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "player")
    protected Set<Survey> surveys;

    public List<Team> getInvitedTeams() {
        return invitedTeams;
    }

    public void setInvitedTeams(List<Team> invitedTeams) {
        this.invitedTeams = invitedTeams;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "player")
    private Availability availability;

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    /**
     * @return the availability
     */
    public Availability getAvailability() {
        return availability;
    }

    public Set<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(Set<Survey> surveys) {
        this.surveys = surveys;
    }

    /**
     * Returns the survey pertaining to a particular tournament
     * 
     * @param tournament The tournament whose survey we are interested in
     * @return Survey object pertaining to that tournament
     */
    public Survey getSurveyByTournament(Tournament tournament) {
        for(Survey s : surveys) {

            if(s.getTournament().equals(tournament)) {
                return s;
            }
        }
        return null;
    }

    /**
     * Adds survey to the list of surveys for player
     * 
     * @param survey the survey to be added top player
     */
    public void addSurvey(Survey survey) {
        if(survey == null || surveys.contains(survey)) {
            return;
        }
        surveys.add(survey);
    }

    /**
     * Removes survey from list of surveys for player
     * 
     * @param survey the survey to be removed from player
     */
    public void removeSurvey(Survey survey) {
        if(survey == null || !surveys.contains(survey)) {
            return;
        }
        surveys.remove(survey);
    }
}
