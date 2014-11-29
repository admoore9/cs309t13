package tests;

import java.util.List;
import java.util.Random;

import org.junit.Test;

import edu.iastate.dao.GameDao;
import edu.iastate.dao.MemberDao;
import edu.iastate.dao.TeamDao;
import edu.iastate.dao.TournamentDao;
import edu.iastate.models.Game;
import edu.iastate.models.Member;
import edu.iastate.models.Team;
import edu.iastate.models.Tournament;
import edu.iastate.models.Member.UserType;

public class TournamentTests {
    @Test
    public void addTournamentTest() {
        TournamentDao tournamentdao = new TournamentDao();
        Tournament tournament = new Tournament();
        tournament.setDoubleElimination(false);
        tournament.setMaxPlayers(8);
        tournament.setMinPlayers(2);
        tournament.setName("Tennis");
        tournament.setOfficialsPerGame(1);
        tournament.setStarted(false);
        tournament.setTeamsPerGame(2);
        tournamentdao.saveTournament(tournament);
    }
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
        for (Team team: teams) {
            System.out.println(team.getName());
        }
    }
    
    @Test
    public void addTeamsToTournament() {
        Team team = new Team();
        TeamDao teamdao = new TeamDao();
        MemberDao playerdao = new MemberDao();
        Member player = new Member();
        TournamentDao tournamentdao = new TournamentDao();
        
        for(int i=350; i<351; i++) {
            player.setName("Sam" + i);
            player.setUsername("sam" + i);
            player.setUserType(UserType.PLAYER);
            player.setPassword("123");
            playerdao.save(player);
            team.setName("test" + i);
            team.setTournament(tournamentdao.getTournamentById(1, true, true));
            team.setTeamLeader(playerdao.getMemberById(i));
            team.setTeamSkillLevel(100-randInt(10, 60));
            teamdao.saveTeam(team);
        }
       
    }
    
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

    
    @Test
    public void removeGameFromTournament() {
        GameDao gamedao = new GameDao();
        List<Game> games = gamedao.getAllGames();
        TournamentDao tournamentdao = new TournamentDao();
        for(Game game: games) {
            game.setTournament(tournamentdao.getTournamentById(2, true, true));
            game.setNextGame(null);
            gamedao.saveGame(game);
        }
    }

    @Test
    public void gameCoordinatorTest() {
        TournamentDao tournamentdao = new TournamentDao();
        MemberDao memberdao = new MemberDao();
        Tournament tournament = tournamentdao.getTournamentById(2, true, true);
        tournament.setGameCoordinator(memberdao.getMemberById(1));
        tournamentdao.saveTournament(tournament);
        System.out.println(tournamentdao.getTournamentById(1, true, true).getGameCoordinator().getName());
    }
    
    @Test
    public void tournamentGetTournamentByCoordIdTest() {
        TournamentDao tournamentdao = new TournamentDao();
        MemberDao memberDao = new MemberDao();
        List<Tournament> tournaments = tournamentdao.getTournamentByCoordinator(memberDao.getMemberById(1));
        for(Tournament tournament : tournaments) {
            System.out.println(tournament.getGameCoordinator().getName() + " " + tournament.getName());
        }
    }
}
