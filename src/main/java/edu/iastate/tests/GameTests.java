package edu.iastate.tests;

import java.util.List;

import org.junit.Test;

import edu.iastate.dao.GameDao;
import edu.iastate.dao.PlayerDao;
import edu.iastate.models.Game;
import edu.iastate.models.Player;

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
}
