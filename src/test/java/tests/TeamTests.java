package tests;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import edu.iastate.dao.GameDao;
import edu.iastate.dao.MemberDao;
import edu.iastate.dao.TeamDao;
import edu.iastate.dao.TournamentDao;
import edu.iastate.models.Game;
import edu.iastate.models.Member;
import edu.iastate.models.Team;

public class TeamTests {
    MemberDao playerdao = new MemberDao();
    @Test
    public void saveTeamTest() {
        Team team = new Team();
        GameDao gamedao = new GameDao();
        TournamentDao tournamentdao = new TournamentDao();
        team.setAcceptFreeAgents(false);
        team.setGames((Set<Game>)gamedao.getAllGames());
        team.setName("TestInvitedPlayer");
        Set<Member> players = new HashSet<Member>();
        players.add(playerdao.getMemberById(1));
        players.add(playerdao.getMemberById(2));
        team.setTournament(tournamentdao.getTournamentById(1, false, false));
        team.setPlayers(players);
        team.addPlayer(playerdao.getMemberById(3));
        team.addInvitedPlayer(playerdao.getMemberById(4));
        team.setTeamLeader(playerdao.getMemberById(1));
        TeamDao teamdao = new TeamDao();
        teamdao.saveTeam(team);

        /*System.out.println("Names:");
        for (Player player : players) {
            System.out.println(player.getName());
        }*/
    }

    @Test
    public void addPlayerToTeamTest() {
        TeamDao teamdao = new TeamDao();
        Team team = teamdao.getTeamById(13, true, true, true);
        Member player = playerdao.getMemberById(2);
        team.addPlayer(player);
        for (Member p: team.getPlayers()) {
            System.out.println(p.getName());
        }

        //teamdao.saveTeam(team);
    }

    @Test
    public void removePlayerFromTeamTest() {
        TeamDao teamdao = new TeamDao();
        Team team = teamdao.getTeamById(10, true, true, false);
        Member player = playerdao.getMemberById(2);
        team.removePlayer(player);
        for (Member p: team.getPlayers()) {
            System.out.println(p.getId());
        }

        teamdao.saveTeam(team);
    }
}
