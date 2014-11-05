package edu.iastate.models;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
        surveys = new ArrayList<Survey>();
    }

    public Player(String name, String username, String password) {
        super(name, username, password,
                UserType.PLAYER);
    }

    @ManyToMany(mappedBy = "players")
    private List<Team> teams;
    
    @ManyToMany(mappedBy = "invitedPlayers")
    private List<Team> invitedTeams;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "player")
    private List<Survey> surveys;

    public List<Team> getInvitedTeams() {
        return invitedTeams;
    }

    public void setInvitedTeams(List<Team> invitedTeams) {
        this.invitedTeams = invitedTeams;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
    
    public List<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }
    
    /**
     * Returns the survey pertaining to a particular tournament
     * 
     * @param tournament
     * The tournament whose survey we are interested in
     * @return
     * Survey object pertaining to that tournament
     */
    public Survey getSurveyByTournament(Tournament tournament) {
        for(Survey s: surveys) {
            
            if(s.getTournament().equals(tournament)) {
                return s;
            }
        }
        return null;
    }
    
    public void addSurvey(Survey survey) {
        if(survey == null || surveys.contains(survey)) {
            return;
        }
        surveys.add(survey);
    }
    
    public void removeSurvey(Survey survey) {
        if(survey == null || !surveys.contains(survey)) {
            return;
        }
        surveys.remove(survey);
    }
}

