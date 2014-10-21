package edu.iastate.tests;

import java.util.List;

import org.junit.Test;

import edu.iastate.dao.GameDao;
import edu.iastate.dao.PlayerDao;
import edu.iastate.dao.TeamDao;
import edu.iastate.dao.TournamentDao;
import edu.iastate.models.Player;
import edu.iastate.models.Team;
import edu.iastate.models.Tournament;

public class TeamTests {
    @Test
    public void saveTeamTest() {
        Team team = new Team();
        GameDao gamedao = new GameDao();
        PlayerDao playerdao = new PlayerDao();
        TournamentDao tournamentdao = new TournamentDao();
        team.setAcceptFreeAgents(false);
        team.setGames(gamedao.getAllGames());
        team.setName("TestSaveTeam5");
        team.setPlayers(playerdao.getAllPlayers());
        team.setTournament(tournamentdao.getTournamentById(1, false, false));
        TeamDao teamdao = new TeamDao();
        teamdao.saveTeam(team);
        /*System.out.println("Names:");
        for (Player player : players) {
            System.out.println(player.getName());
        }*/
    }
}
