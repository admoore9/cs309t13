package edu.iastate.models;

import java.util.List;

/**
 * Interface for Tournaments.
 * 
 * @author brianshannan
 *
 */
public class Tournament {

    private String name;
    private int minPlayers;
    private int maxPlayers;
    private boolean isDoubleElimination;
    private boolean highScoreWins;
    private List<Team> teams;
    private List<Game> games;
    private boolean isStarted;

    /**
     * Gets the name of the tournament.
     * 
     * @return Returns the name of the tournament.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the tournament.
     * 
     * @param name What to set the name of the tournament too.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the minimum number of players allowed on a team for this tournament.
     * 
     * @return Minimum number of players allowed on a team.
     */
    public int getMinPlayersPerTeam() {
        return minPlayers;
    }

    /**
     * Sets the minimum number of players allowed on a team for this tournament.
     * 
     * @param minPlayers The minimum number of players that should be allowed on
     *            a team.
     */
    public void setMinPlayersPerTeam(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    /**
     * Gets the maximum number of players allowed on a team for this tournament.
     * 
     * @return Maximum number of players allowed on a team.
     */
    public int getMaxPlayersPerTeam() {
        return maxPlayers;
    }

    /**
     * Sets the maximum number of players allowed on a team for this tournament.
     * 
     * @param maxPlayers The maximum number of players that should be allowed on
     *            a team.
     */
    public void setMaxPlayersPerTeam(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    /**
     * Returns whether the team with the highest score wins. False implies the
     * team with the lowest score wins.
     * 
     * @return Whether the team with the highest score wins or the team with the
     *         lowest score.
     */
    public boolean getHighScoreWins() {
        return highScoreWins;
    }

    /**
     * Sets if the team with the highest score wins. This shouldn't be able to
     * be called after startTournament() is called
     * 
     * @param highScoreWins True if the highest score wins, False if the lowest
     *            score wins.
     */
    public void setHighScoreWins(boolean highScoreWins) {
        this.highScoreWins = highScoreWins;
    }

    /**
     * Sets if the tournament is double elimination.
     * 
     * @return True if it is double elimination, False if it is single
     *         elimination.
     */
    public boolean getIsDoubleElimination() {
        return isDoubleElimination;
    }

    /**
     * Sets if the tournament is single elimination. This shouldn't be able to
     * be called after formTournamentBracket() is called.
     * 
     * @param isDoubleElimination True for double elimination, False for single
     *            elimination.
     */
    public void setIsDoubleElimination(boolean isDoubleElimination) {
        this.isDoubleElimination = isDoubleElimination;
    }

    /**
     * Adds a given team to the current tournament. This shouldn't be able to be
     * called after formTournamentBracket() is called. A team should only be
     * able to be added once to a given tournament.
     * 
     * @param team The team to add to the tournament.
     * @return Whether the team was successfully added to the tournament.
     */
    public boolean addTeam(Team team) {
        if(teams.contains(team)) {
            return false;
        }

        teams.add(team);
        return true;
    }

    /**
     * Returns the teams registered for the tournament.
     * 
     * @return The teams registered for the tournament.
     */
    public List<Team> getTeams() {
        return teams;
    }

    /**
     * Removes a team from the current tournament. This shouldn't be able to be
     * called after formTournamentBracket() is called.
     * 
     * @param team The team to remove from the tournament.
     * @return Whether the team was successfully removed.
     */
    public boolean removeTeam(Team team) {
        if(!teams.contains(team)) {
            return false;
        }

        teams.remove(team);
        return true;
    }

    /**
     * Gets the games setup for the current tournament.
     * 
     * @return The list of games setup for this tournament.
     */
    public List<Game> getGames() {
        return games;
    }

    /**
     * Forms the bracket for the current tournament based on the current teams
     * added to the tournaments. No other teams should be able to join after
     * this. Fantasy brackets for the current tournament can be filled out after
     * this. This shouldn't be able to be called if a bracket already exists for
     * this tournament.
     * 
     * @return Whether the bracket was successfully formed
     */
    public boolean formTournamentBracket() {
        throw new UnsupportedOperationException();
    }

    /**
     * Marks the current tournament as started. Fantasy brackets for this
     * tournament shouldn't be able to be filled out after this. A tournament
     * should only be able to be started once.
     */
    public void startTournament() {
        throw new UnsupportedOperationException();
    }
}