package edu.iastate.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.iastate.dao.GameDao;
import edu.iastate.dao.MemberDao;
import edu.iastate.dao.PlayerDao;
import edu.iastate.dao.TeamDao;
import edu.iastate.dao.TournamentDao;
import edu.iastate.models.*;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping(method = RequestMethod.GET)
    public String test(Model model) {
        model.addAttribute("name", "After some changess");

        TournamentDao tournamentdao = new TournamentDao();
        Tournament tournament = tournamentdao.getTournamentById(1, false, false);
        PlayerDao playerdao = new PlayerDao();
        
        Team team = new Team();
        GameDao gamedao = new GameDao();
        team.setAcceptFreeAgents(true);
        team.setName("TestAll3");
        team.setTournament(tournament);
        team.setGames(tournament.getGames());
        team.setPlayers(playerdao.getAllPlayers());
        TeamDao teamdao = new TeamDao();
        teamdao.saveTeam(team);
         
        

        return "test";
    }
}
