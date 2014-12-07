package edu.iastate.models;

import java.util.HashSet;
import java.util.LinkedHashSet;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "Team")
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private int id;

    @Column(name = "team_name")
    private String name;
    
    @JsonIgnore
    @Column(name = "team_password")
    private String password;

    @Column(name = "accepts_free_agents")
    private boolean acceptFreeAgents;

    @JoinTable(name = "teamplayermapper", joinColumns = {@JoinColumn(name = "team_id", referencedColumnName = "team_id")}, inverseJoinColumns = {@JoinColumn(name = "member_id", referencedColumnName = "member_id")})
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Member> players;

    @JoinTable(name = "teaminvitedplayermapper", joinColumns = {@JoinColumn(name = "team_id", referencedColumnName = "team_id")},
            inverseJoinColumns = {@JoinColumn(name = "member_id", referencedColumnName = "member_id")})
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Member> invitedPlayers;

    @JsonIgnore
    @ManyToMany(mappedBy = "teams")
    private Set<Game> games;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member teamLeader;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @Column(name = "team_skill")
    private int teamSkillLevel;

    @JsonIgnore
    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    private Set<Score> scores;

    @JsonIgnore
    @OneToMany(mappedBy = "winner")
    private Set<Game> wonGames;

    public Team() {
        players = new HashSet<Member>();
        games = new HashSet<Game>();
        invitedPlayers = new HashSet<Member>();
        scores = new HashSet<Score>();
    }

    public Team(int id, String name, boolean acceptFreeAgents, Set<Member> players, Set<Game> games, Member teamLeader) {
        this.id = id;
        this.name = name;
        this.acceptFreeAgents = acceptFreeAgents;
        this.players = players;
        this.games = games;
        this.teamLeader = teamLeader;
    }

    public Member getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(Member teamLeader) {
        this.teamLeader = teamLeader;
        addPlayer(teamLeader);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAcceptFreeAgents() {
        return acceptFreeAgents;
    }

    public void setAcceptFreeAgents(boolean acceptFreeAgents) {
        this.acceptFreeAgents = acceptFreeAgents;
    }

    public Set<Member> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Member> players) {
        this.players = players;
        calculateSkillLevel();
    }

    public Set<Member> getInvitedPlayers() {
        return invitedPlayers;
    }

    public void setInvitedPlayers(Set<Member> invitedPlayers) {
        this.invitedPlayers = invitedPlayers;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Set<Score> getScores() {
        return scores;
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }

    public int getTeamSkillLevel() {
        return teamSkillLevel;
    }

    /**
     * Calculates the skill level of the team based on skill level of players of
     * team
     */
    private void calculateSkillLevel() {
        int skillLevel = 0;
        for(Member player : players) {
            Survey s = player.getSurveyByTournament(tournament);
            if(s == null) {
                skillLevel += 0;
                continue;
            }
            skillLevel += s.getSurveyScore();
        }
        teamSkillLevel = skillLevel / players.size();
    }

    /**
     * Adds player to this team. Does nothing if player is null or player
     * already exists in current team
     * 
     * @param player The player to be added
     * @return -1 if null or player already exists 0 if maximum has reached 1 if
     */
    public int addPlayer(Member player) {
        if(player == null || this.players.contains(player)) {

            return -1;
        }
        if(this.players.size() == tournament.getMaxPlayers()) {
            return 0;
        }
        if(player.isPlayerInTournament(tournament)) {
            return -2;
        }
        this.players.add(player);
        removeInvitedPlayer(player);
        calculateSkillLevel(); // Updates the skill level
        return 1;
    }

    /**
     * Removes player from team. Does nothing if player is null or player does
     * not exist in team
     * 
     * @param player The player to be removed
     */
    public void removePlayer(Member player) {
        if(player == null || !this.players.contains(player)  || player.equals(teamLeader)) {
            return;
        }
        player.removeSurvey(player.getSurveyByTournament(this.tournament));
        this.players.remove(player);
        calculateSkillLevel(); // Updates the skill level
    }

    /**
     * Adds player to this team's invited player list. Does nothing if player is
     * null or player already exists in current invited list
     * 
     * @param player The player to be added
     * @return -1 if null or player already exists 0 if player is already in
     *         team 1 if successful
     * 
     */
    public int addInvitedPlayer(Member player) {
        if(player == null || this.invitedPlayers.contains(player)) {
            return -1;
        }
        if(this.players.contains(player)) {
            return 0;
        }

        this.invitedPlayers.add(player);
        return 1;
    }

    /**
     * Removes player from team. Does nothing if player is null or player does
     * not exist in team
     * 
     * @param player The player to be removed
     */
    public void removeInvitedPlayer(Member player) {
        if(player == null || !this.invitedPlayers.contains(player)) {
            return;
        }
        this.invitedPlayers.remove(player);
    }

    /**
     * Returns true if this team has minimum number of required players master
     * 
     * @return true of condition is met
     */
    public boolean hasMinPlayers() {
        return this.players.size() >= tournament.getMinPlayers();
    }

    public void setTeamSkillLevel(int teamSkillLevel) {
        this.teamSkillLevel = teamSkillLevel;
    }

    public Availability getTeamAvailability(Team team) {
        Availability teamAvailability = initializeAvailability(new Availability());
        Set<Member> players = team.getPlayers();
        for (Member player : players) {
            Availability playerAvailability = player.getAvailability();
            for (Day day : playerAvailability.getDays())
                for (Period period : day.getPeriods())
                    if (!period.isAvailable())
                        teamAvailability.setPeriodAvailability(period, false);
        }
        return teamAvailability;
    }

    private Availability initializeAvailability(Availability availability) {
        LinkedHashSet<Day> days = new LinkedHashSet<Day>();
        for (Day.WeekDay weekDay : Day.WeekDay.values()) {
            Day day = new Day(weekDay);
            for (Period.Slot slot : Period.Slot.values())
                day.addPeriod(new Period(slot).setAvailable(true));
            days.add(day);
        }
        availability.setDays(days);
        return availability;
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
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }
}
