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
@RequestMapping("/team")
public class TeamController {

    @RequestMapping(method = RequestMethod.GET)
    public String test(Model model) {
        model.addAttribute("name", "Team List test");
        Team team = new Team();
        team.setAcceptFreeAgents(false);
        team.setName("JavaJuggler");
        
        TeamDao teamdao = new TeamDao();
        teamdao.saveTeam(team);       
        return "teamList";
    }
}
