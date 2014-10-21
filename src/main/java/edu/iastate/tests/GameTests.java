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
    public void returnAllGamesTest() {
        GameDao gameDao = new GameDao();
        List<Game> games = gameDao.getAllGames();
//        System.out.println("Names:");
//        for (Game game: games) {
//            System.out.println(game.getGameLocation());
//        }
    }
	
	@Test
	public void getGameByIdTest() {
		GameDao gamedao = new GameDao();
		Game game = gamedao.getGameById(2, true);
		System.out.println(game.getGameLocation());
	}
	
	@Test
	public void saveGameTest() {
		GameDao gamedao = new GameDao();
		TournamentDao tournamentdao = new TournamentDao();
		Game game = new Game();
		Date date = new Date();
		TeamDao teamdao = new TeamDao();
		game.setGameLocation("Test Location2");
		game.setGameTime(date);
		game.setNextGame(gamedao.getGameById(2, false));
		game.setTeams(teamdao.getAllTeams());
		game.setTournament(tournamentdao.getTournamentById(1, false, false));
		gamedao.saveGame(game);
	}
	
	@Test
	public void modifyGameTest() {
		GameDao gamedao = new GameDao();
		Game game = new Game();
		game = gamedao.getGameById(2, false);
		game.setNextGame(gamedao.getGameById(1, false));
		gamedao.saveGame(game);
	}
}
