package edu.iastate.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.iastate.dao.TeamDao;
import edu.iastate.models.Player;
import edu.iastate.models.Team;

@Controller
@RequestMapping("/team")
public class TeamController {

    @RequestMapping(method = RequestMethod.GET)
    public String getTeam(Model model) {

        TeamDao teamdao = new TeamDao();
        Team team = teamdao.getTeamById(2, true, true);
        model.addAttribute("teams", team.getGames());
        return "team";
    }

    @RequestMapping(value = "/{id}/players", method = RequestMethod.GET)
    public @ResponseBody List<Player> getPlayersForTeam(@PathVariable int id) {
        TeamDao teamDao = new TeamDao();
        Team team = teamDao.getTeamById(id, false, true);
        for(Player player : team.getPlayers()) {
            // Causing circular references... Should actually fix that
            player.setSurveys(null);
            player.setTeams(null);
        }
        return team.getPlayers();
    }
}
