package tests;

import java.util.List;

import org.junit.Test;

import edu.iastate.dao.GameDao;
import edu.iastate.dao.TeamDao;
import edu.iastate.dao.TournamentDao;
import edu.iastate.models.Game;
import edu.iastate.models.Team;
import edu.iastate.models.Tournament;

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
    
    @Test
    public void testSortTeams() {
        TeamDao teamdao = new TeamDao();
        List<Team> teams = teamdao.getAllTeams();
        for (Team team: teams) {
            System.out.println(team.getName());
        }
        Tournament tournament = new Tournament();
        //tournament.sortTeamsBasedOnSkill(teams);
//        for (Team team: teams) {
//            System.out.println(team.getName());
//        }
    }
    
    @Test
    public void testFormBracket() {
        TournamentDao td = new TournamentDao();
        Tournament t = td.getTournamentById(1, true, true);
        GameDao gameDao = new GameDao();
        t.formBracket(gameDao);
    }
}
