package edu.iastate.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.iastate.dao.PlayerDao;
import edu.iastate.dao.TeamDao;
import edu.iastate.models.Game;
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

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody boolean createTeam(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "acceptFreeAgents") boolean acceptFreeAgents,
            @RequestParam(value = "teamLeaderId") int teamLeaderId) {
        PlayerDao playerDao = new PlayerDao();
        Player teamLeader = playerDao.getPlayerById(teamLeaderId);

        if(teamLeader == null) {
            return false;
        }

        Team team = new Team();
        team.setName(name);
        team.setAcceptFreeAgents(acceptFreeAgents);
        team.setTeamLeader(teamLeader);

        TeamDao teamDao = new TeamDao();
        teamDao.saveTeam(team);
        return true;
    }

    /**
     * Returns the team given by id as JSON.
     * 
     * @param id The id of the team.
     * @return JSON representation of the team given by id.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Team getTeamById(@PathVariable int id) {
        TeamDao teamDao = new TeamDao();
        return teamDao.getTeamById(id, false, true);
    }

    /**
     * Returns the games that the team given by id has taken part in as JSON.
     * 
     * @param id The id of the team
     * @return JSON representation of the games team has been in.
     */
    @RequestMapping(value = "/{id}/games", method = RequestMethod.GET)
    public @ResponseBody List<Game> getGamesByTeam(@PathVariable int id) {
        TeamDao teamDao = new TeamDao();
        return teamDao.getTeamById(id, true, false).getGames();
    }
}
