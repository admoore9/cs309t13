package edu.iastate.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonManagedReference;

import edu.iastate.dao.GameDao;
import edu.iastate.utils.MathUtils;

/**
 * Tournament class
 *
 * @author brianshannan
 *
 */

@Entity
@Table(name = "Tournament")
public class Tournament {
    @Id
    @GeneratedValue
    @Column(name = "tournament_id")
    private int id;

    @Column(name = "tournament_name")
    private String name;

    @Column(name = "min_players")
    private int minPlayers;

    @Column(name = "max_players")
    private int maxPlayers;

    @Column(name = "teams_per_game")
    private int teamsPerGame;

    @Column(name = "officials_per_game")
    private int officialsPerGame;

    // TODO: make this do stuff
    @Column(name = "is_double_elimination")
    private boolean isDoubleElimination;

    @Column(name = "is_started")
    private boolean isStarted;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tournament")
    private List<Team> teams;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tournament")
    private List<Game> games;

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

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getTeamsPerGame() {
        return teamsPerGame;
    }

    public void setTeamsPerGame(int teamsPerGame) {
        this.teamsPerGame = teamsPerGame;
    }

    public int getOfficialsPerGame() {
        return officialsPerGame;
    }

    public void setOfficialsPerGame(int officialsPerGame) {
        this.officialsPerGame = officialsPerGame;
    }

    public boolean isDoubleElimination() {
        return isDoubleElimination;
    }

    public void setDoubleElimination(boolean isDoubleElimination) {
        this.isDoubleElimination = isDoubleElimination;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean isStarted) {
        this.isStarted = isStarted;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    /**
     * Adds a team to the tournament, doesn't add the team if it is null or
     * already is in the tournament.
     *
     * @param team The team to add to the tournament.
     */
    public void addTeam(Team team) {
        if(team == null || this.teams.contains(team)) {
            return;
        }
        this.teams.add(team);
        team.setTournament(this);
    }

    /**
     * Removes a team from the tournament.
     *
     * @param team The team to remove from the tournament.
     */
    public void removeTeam(Team team) {
        if(team == null || !this.teams.contains(team)) {
            return;
        }
        this.teams.remove(team);
        team.setTournament(null);
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    /**
     * Determines if a bracket has already been formed for the tournament. A
     * bracket has been formed if there are already games linked to the
     * tournament.
     *
     * @return
     */
    public boolean isBracketFormed() {
        return !this.games.isEmpty();
    }

    /**
     * Forms the bracket for the tournament. Doesn't do anything if the bracket
     * has already been formed.
     */
    public void formBracket(GameDao gameDao) {
        if(this.isBracketFormed()) {
            return;
        }

        // Get number of rounds without the play in games
        int roundsWithoutPlayin = (int) Math.floor(MathUtils.log(this.teams.size(), this.teamsPerGame));
        int numGamesFirstFullRound = (int) Math.pow(this.teamsPerGame, roundsWithoutPlayin);
        int leftoverTeams = this.teams.size() - numGamesFirstFullRound;
        int leftoverTeamsPerPlayinGame = this.teamsPerGame - 1;

        // Get teams for play in games
        int numPlayinGames = (int) Math.ceil(1.0 * leftoverTeams / leftoverTeamsPerPlayinGame);
        int numPlayinTeams = leftoverTeams + numPlayinGames;

        List<Team> nonPlayinTeams = this.teams.subList(numPlayinTeams, this.teams.size());
        int roundNumber = 1;

        List<Game> playinGames = new ArrayList<Game>();
        if(numPlayinGames != 0) {
            List<Team> teamsPlayinGames = this.teams.subList(0, numPlayinTeams);
            playinGames = groupTeamsIntoGames(teamsPlayinGames, roundNumber, numPlayinGames);
            roundNumber++;
        }

        // The teams that didn't have a play in game
        int numNonPlayinGames = (int) Math.ceil(1.0 * nonPlayinTeams.size() / this.teamsPerGame);
        List<Game> secondRoundNonPlayinGames = groupTeamsIntoGames(nonPlayinTeams, roundNumber, numNonPlayinGames);
        List<Game> secondRoundPlayinGames = new ArrayList<Game>();
        for(int i = 0; i < numNonPlayinGames; i++) {
            Game game = new Game();
            game.setTournament(this);
            game.setRoundNumber(2);
            secondRoundPlayinGames.add(game);
        }

        List<Game> secondRoundGames = new ArrayList<Game>();
        secondRoundGames.addAll(secondRoundPlayinGames);
        secondRoundGames.addAll(secondRoundNonPlayinGames);
        System.out.println(secondRoundPlayinGames.size());
        System.out.println(secondRoundNonPlayinGames.size());
        System.out.println(secondRoundGames.size());

        formRoundsAndLink(secondRoundGames, roundNumber, gameDao);

        if(numPlayinGames != 0) {
            List<Integer> playinGamesToGames = getBalancedTeamsPerGame(numPlayinGames, secondRoundPlayinGames.size());

            int count = 0;
            for(int i = 0; i < playinGamesToGames.size(); i++) {
                Game nextGame = secondRoundPlayinGames.get(i);
                for(int j = 0; j < playinGamesToGames.get(i); j++) {
                    Game game = playinGames.get(count);
                    game.setNextGame(nextGame);
                    gameDao.createGame(game);
                    count++;
                }
            }
        }
    }

    /**
     * Forms games for the given teams.
     *
     * @param currRoundTeams The teams to form games for.
     * @return A list of games formed based on the given teams.
     */
    public List<Game> groupTeamsIntoGames(List<Team> currRoundTeams, int roundNumber, int gamesNeeded) {
        List<Integer> teamsPerGame = getBalancedTeamsPerGame(currRoundTeams.size(), gamesNeeded);
        List<Game> currRoundGames = new ArrayList<Game>();

        // int count = 0;
        for(int i = 0; i < gamesNeeded; i++) {
            Game game = new Game();
            game.setTournament(this);
            game.setRoundNumber(roundNumber);
            for(int j = 0; j < teamsPerGame.get(i); j++) {
                // game.addTeam(this.teams.get(count));
                // count++;
            }
            currRoundGames.add(game);
        }

        return currRoundGames;
    }

    public void formRoundsAndLink(List<Game> currRoundGames, int roundNumber, GameDao gameDao) {
        if(currRoundGames.size() == 1) {
            Game game = currRoundGames.get(0);
            game.setTournament(this);
            game.setRoundNumber(roundNumber);
            gameDao.createGame(game);
            return;
        }

        // Form next round
        int nextRoundLen = (int) Math.ceil(1.0 * currRoundGames.size() / this.teamsPerGame);
        List<Integer> teamsPerGame = getBalancedTeamsPerGame(currRoundGames.size(), nextRoundLen);
        List<Game> nextRoundGames = new ArrayList<Game>();

        for(int i = 0; i < nextRoundLen; i++) {
            Game game = new Game();
            nextRoundGames.add(game);
        }

        // Link next round with next next round
        formRoundsAndLink(nextRoundGames, roundNumber + 1, gameDao);

        // Link current round to next round
        int count = 0;
        for(int i = 0; i < nextRoundLen; i++) {
            Game nextGame = nextRoundGames.get(i);
            for(int j = 0; j < teamsPerGame.get(i); j++) {
                Game game = currRoundGames.get(count);
                game.setNextGame(nextGame);
                game.setTournament(this);
                game.setRoundNumber(roundNumber);
                gameDao.createGame(game);
                count++;
            }
        }
    }

    /**
     * Tries to balance (as evenly as possible) the number of teams that should
     * be in each game. Assumes there will be one winning team per game.
     *
     * @param currRoundCount The number of games in the current round.
     * @param nextRoundCount The number of games in the next round.
     * @return A list with the game number as the index and the number of teams
     *         that should be in it as the value at that index.
     */
    public List<Integer> getBalancedTeamsPerGame(int currRoundCount, int nextRoundCount) {
        Integer[] arr = new Integer[nextRoundCount];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = 0;
        }

        for(int i = 0; i < currRoundCount; i++) {
            arr[i % nextRoundCount]++;
        }

        return Arrays.asList(arr);
    }

    @Override
    public String toString() {
        return this.name;
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
        Tournament other = (Tournament) obj;
        if(id != other.id)
            return false;
        return true;
    }
}
