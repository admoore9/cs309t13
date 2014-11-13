package tests;

import java.util.List;
import java.util.Random;

import org.junit.Test;

import edu.iastate.dao.GameDao;
import edu.iastate.dao.PlayerDao;
import edu.iastate.dao.TeamDao;
import edu.iastate.dao.TournamentDao;
import edu.iastate.models.Game;
import edu.iastate.models.Player;
import edu.iastate.models.Team;
import edu.iastate.models.Tournament;
import edu.iastate.models.Member.UserType;

public class TournamentTests {
//    @Test
//    public void returnAllPlayerTest() {
//        TournamentDao tournamentdao = new TournamentDao();
//        List<Game> games = tournamentdao.getTournamentById(1, true, true).getGames();
//        System.out.println("Names:");
//        for (Game game : games) {
//            System.out.println(game.getGameLocation());
//        }
//    }
//    
//    @Test
//    public void testSortTeams() {
//        TeamDao teamdao = new TeamDao();
//        List<Team> teams = teamdao.getAllTeams();
//        for (Team team: teams) {
//            System.out.println(team.getName());
//        }
//        Tournament tournament = new Tournament();
//        //tournament.sortTeamsBasedOnSkill(teams);
//        for (Team team: teams) {
//            System.out.println(team.getName());
//        }
//    }
    
//    @Test
//    public void addTeamsToTournament() {
//        Team team = new Team();
//        TeamDao teamdao = new TeamDao();
//        PlayerDao playerdao = new PlayerDao();
//        Player player = new Player();
//        TournamentDao tournamentdao = new TournamentDao();
//        
//        for(int i=131; i<132; i++) {
//            player.setName("Sam" + i);
//            player.setUsername("sam" + i);
//            player.setUserType(UserType.PLAYER);
//            player.setPassword("123");
//            playerdao.saveMember(player);
//            team.setName("test" + i);
//            team.setTournament(tournamentdao.getTournamentById(1, true, true));
//            team.setTeamLeader(playerdao.getPlayerById(i, true));
//            team.setTeamSkillLevel(100-randInt(10, 60));
//            teamdao.saveTeam(team);
//        }
//       
//    }
    
    public static int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    
    @Test
    public void testFormBracket() {
        TournamentDao td = new TournamentDao();
        Tournament t = td.getTournamentById(1, true, true);
        GameDao gameDao = new GameDao();
        t.formBracket(gameDao);
    }
    
//    @Test
//    public void removeGameFromTournament() {
//        GameDao gamedao = new GameDao();
//        List<Game> games = gamedao.getAllGames();
//        TournamentDao tournamentdao = new TournamentDao();
//        for(Game game: games) {
//            //game.setTournament(tournamentdao.getTournamentById(2, true, true));
//            game.setNextGame(null);
//            gamedao.saveGame(game);
//        }
//    }
}
