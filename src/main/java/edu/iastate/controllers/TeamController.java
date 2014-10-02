package edu.iastate.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.iastate.dao.TeamDao;
import edu.iastate.dao.TournamentDao;
import edu.iastate.models.*;

@Controller
@RequestMapping("/team")
public class TeamController {

	@RequestMapping(method = RequestMethod.GET)
    public String getTeam(Model model) {
        TeamDao TeamDao = new TeamDao();
        List<Team> teams = TeamDao.getAllTeams();
        model.addAttribute("teams", teams);
        return "team";
    }

    /*@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Team getTeamById(@PathVariable int id) {
        TeamDao TeamDao = new TeamDao();
        Team Team = TeamDao.getTeamById(id, true, true);
        return Team;
    }*/
}
