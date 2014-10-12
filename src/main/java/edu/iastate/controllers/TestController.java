package edu.iastate.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.iastate.dao.TeamDao;
import edu.iastate.dao.TournamentDao;
import edu.iastate.models.*;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping(method = RequestMethod.GET)
    public String test(Model model) {
        model.addAttribute("name", "After some changess");

        /*Tournament test = new Tournament();
        test.setDoubleElimination(true);
        //test.setId(1234);
        test.setMaxPlayers(5);
        test.setMinPlayers(3);
        test.setName("Basketball");
        test.setStarted(false);
        Team team1 = new Team();
        Game game1 = new Game();
        List<Game> gameList = null;
        List<Team> teamList = null;
        gameList.add(game1);
        teamList.add(team1);
        test.setGames(gameList);
        test.setTeams(teamList);*/
        
        
        //TournamentDao tournamentDao = new TournamentDao();
        //tournamentDao.saveTournament(test);
        TournamentDao tournamentdao = new TournamentDao();
        Team team = new Team();
        team.setAcceptFreeAgents(true);
        team.setName("test");
        team.setTournament(tournamentdao.getTournamentById(1, false, false));
        
        TeamDao teamdao = new TeamDao();
        teamdao.saveTeam(team);
         
        

        return "test";
    }
}
