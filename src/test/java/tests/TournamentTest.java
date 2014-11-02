package tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.iastate.models.Game;
import edu.iastate.models.Team;
import edu.iastate.models.Tournament;

public class TournamentTest {
    Tournament tournament;

    @Before
    public void setUp() {
        tournament = new Tournament();
    }

    @Test
    public void testGetBalancedTeamsBalanced() {
        Integer[] expected = {3, 3, 3, 3};
        List<Integer> actual = tournament.getBalancedTeamsPerGame(12, 4);
        Assert.assertEquals(Arrays.asList(expected), actual);
    }

    @Test
    public void testGetBalancedTeamsUnbalanced() {
        Integer[] expected = {3, 3, 2, 2};
        List<Integer> actual = tournament.getBalancedTeamsPerGame(10, 4);
        Assert.assertEquals(Arrays.asList(expected), actual);
    }

    @Test
    public void testFormRound() {
        tournament.setTeamsPerGame(2);
        List<Game> games = new ArrayList<Game>();
        for(int i = 0; i < 8; i++) {
            games.add(new Game());
        }

        List<Game> actual = tournament.formNextRound(games, 5, null);
        Assert.assertEquals(4, actual.size());
        for(int i = 0; i < actual.size(); i++) {
            Assert.assertEquals(5, actual.get(i).getRoundNumber());
        }
        for(int i = 0; i < games.size(); i++) {
            Assert.assertSame(actual.get(i / 2), games.get(i).getNextGame());
        }
    }

    @Test
    public void testGroupTeamsIntoGames() {
        tournament.setTeamsPerGame(2);
        List<Team> teams = new ArrayList<Team>();
        for(int i = 0; i < 10; i++) {
            teams.add(new Team());
        }

        List<Game> gamesProduced = tournament.groupTeamsIntoGames(teams, 5, null);
        Assert.assertEquals(5, gamesProduced.size());
    }
}
