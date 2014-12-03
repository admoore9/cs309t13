package edu.iastate.models;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id", unique = true, nullable = false)
    private int member_id;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "sex")
    private String sex;

    @Column(name = "height")
    private Integer height;

    @Column(name = "weight")
    private Integer weight;

    public enum UserType {
        PLAYER, OFFICIAL, COORDINATOR, ADMIN
    };

    @Column(name = "context")
    private UserType context;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "user_type")
    private UserType userType;
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "gameCoordinator")
    Set<Tournament> managingTournament;

    public Member() {
        this.userType = UserType.PLAYER;
    }

    public Member(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.userType = UserType.PLAYER;
        this.context = UserType.PLAYER;
        this.height = -1;
        this.weight = -1;
    }

    /**
     * @return the member_id
     */
    public int getMember_id() {
        return member_id;
    }

    /**
     * @param member_id the member_id to set
     */
    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    /**
     * Get user type
     * 
     * @return userType
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * Set user type
     * 
     * @param userType
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    /**
     * Get context
     * 
     * @return context
     */
    public UserType getContext() {
        return context;
    }

    /**
     * Set context
     * 
     * @param context
     */
    public void setContext(UserType view) {
        this.context = view;
    }

    /**
     * Get id
     * 
     * @return
     */
    public int getId() {
        return member_id;
    }

    /**
     * Set id
     * 
     * @param id
     */
    public void setId(int id) {
        this.member_id = id;
    }

    /**
     * Get name
     * 
     * @return
     */
    public String getName() {
        return name;
    }
    
    public Set<Tournament> getManagingTournament() {
        return managingTournament;
    }

    public void setManagingTournament(Set<Tournament> managingTournament) {
        this.managingTournament = managingTournament;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + member_id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Member other = (Member) obj;
        if (member_id != other.member_id)
            return false;
        return true;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    // =========Player================

    @JsonIgnore
    @ManyToMany(mappedBy = "players", fetch = FetchType.EAGER)
    private Set<Team> teams;

    @JsonIgnore
    @ManyToMany(mappedBy = "invitedPlayers", fetch = FetchType.EAGER)
    private Set<Team> invitedTeams;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "player")
    protected Set<Survey> surveys;

    public Set<Team> getInvitedTeams() {
        return invitedTeams;
    }

    public void setInvitedTeams(Set<Team> invitedTeams) {
        this.invitedTeams = invitedTeams;
    }

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "player")
    private Availability availability;

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    /**
     * @return the availability
     */
    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
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
        for (Survey s : surveys) {

            if (s.getTournament().equals(tournament)) {
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
        if (survey == null || surveys.contains(survey)) {
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
        if (survey == null || !surveys.contains(survey)) {
            return;
        }
        surveys.remove(survey);
    }
    
    /**
     * Checks if the player has already registered for a tournament
     * 
     * @param tournament the tournament to check if this player is registered 
     * @return true if player is registered, false otherwise
     */
    public boolean isPlayerInTournament(Tournament tournament) {
        for(Team t: teams) {
            if(t.getTournament().equals(tournament)){
                return true;
            }
        }
        return false;
    }

    // -------------End Player-------------------------

    // ============Official=============
    @JsonIgnore
    @ManyToMany(mappedBy = "officials")
    private List<Game> games;

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
    // ----------ENd Official------------

    // =========Admin===============

    // -----------End Admin---------
}
