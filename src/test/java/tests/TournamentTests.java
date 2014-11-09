package tests;

import java.util.List;

import org.junit.Test;

import edu.iastate.dao.TournamentDao;
import edu.iastate.models.Game;

public class TournamentTests {
    @Test
    public void returnAllPlayerTest() {
        TournamentDao tournamentdao = new TournamentDao();
        List<Game> games = tournamentdao.getTournamentById(1, true, true).getGames();
        System.out.println("Names:");
        for (Game game : games) {
            System.out.println(game.getGameLocation());
        }
    }
}
