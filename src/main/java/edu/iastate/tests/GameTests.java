package edu.iastate.tests;


import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import edu.iastate.dao.GameDao;
import edu.iastate.dao.PlayerDao;
import edu.iastate.dao.TeamDao;
import edu.iastate.dao.TournamentDao;
import edu.iastate.models.Game;
import edu.iastate.models.Player;
import edu.iastate.models.Team;

public class GameTests {

    @Test
    public void getGameByIdTest() {
        GameDao gamedao = new GameDao();
        Game game = gamedao.getGameById(2, true);
        System.out.println(game.getGameLocation());
    }

    @Test
    public void modifyGameTest() {
        GameDao gamedao = new GameDao();
        Game game = new Game();
        game = gamedao.getGameById(8, false);
        game.setNextGame(gamedao.getGameById(1, false));
        gamedao.saveGame(game);
    }

    @Test
    public void saveGameTest() {
        Game game = new Game();
        game.setGameLocation("saveGameTestLocation");
        game.setGameTime(new Date());
        List<Team> teams = new ArrayList<Team>();
        TeamDao teamdao = new TeamDao();
        teams.add(teamdao.getTeamById(1, false, false));
        teams.add(teamdao.getTeamById(1, false, false));
        game.setTeams(teams);
        TournamentDao tournamentdao = new TournamentDao();
        game.setTournament(tournamentdao.getTournamentById(1, false, false));
        GameDao gamedao = new GameDao();
        gamedao.saveGame(game);
        System.out.println(gamedao.getGameById(2, false).getGameLocation());
    }

    @Test
    public void returnAllGamesTest() {
        GameDao gameDao = new GameDao();
        List<Game> games = gameDao.getAllGames();
        System.out.println("Location:");
        for (Game game: games) {
            System.out.println(game.getGameLocation());
        }
    }
}
