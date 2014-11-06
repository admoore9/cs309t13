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

    /**
     * Creates a team.
     * 
     * POST request data: name, acceptFreeAgents, teamLeaderId
     * 
     * @param name The name of the team
     * @param acceptFreeAgents Whether to accept free agents onto the team
     * @param teamLeaderId The id of the leader of the team
     * @return true if the team was successfully created, false otherwise
     */
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

    /**
     * Sets the name of the team given by id to the given name.
     * 
     * POST request data: name
     * 
     * @param id The id of the team
     * @param name The new name for the team
     * @return true if the name was successfully changed, false otherwise.
     */
    @RequestMapping(value = "/{id}/name", method = RequestMethod.POST)
    public @ResponseBody boolean setTeamName(
            @PathVariable int id,
            @RequestParam(value = "name") String name) {
        TeamDao teamDao = new TeamDao();
        Team team = teamDao.getTeamById(id, false, false);

        if(team == null) {
            return false;
        }

        team.setName(name);
        teamDao.saveTeam(team);
        return true;
    }

    /**
     * Sets whether the team accepts free agents.
     * 
     * POST request data: acceptFreeAgents
     * 
     * @param id The id of the team.
     * @param acceptFreeAgents Whether the team should accept free agents.
     * @return true if acceptFreeAgents was successfully updated, false
     *         otherwise.
     */
    @RequestMapping(value = "/{id}/acceptFreeAgents", method = RequestMethod.GET)
    public @ResponseBody boolean setAcceptFreeAgents(
            @PathVariable int id,
            @RequestParam(value = "acceptFreeAgents") boolean acceptFreeAgents) {
        TeamDao teamDao = new TeamDao();
        Team team = teamDao.getTeamById(id, false, false);

        if(team == null) {
            return false;
        }

        team.setAcceptFreeAgents(acceptFreeAgents);
        teamDao.saveTeam(team);
        return false;
    }

    /**
     * Changes the given teams leader to the player with the given id.
     * 
     * POST request data: teamLeaderId
     * 
     * @param id The id of the team.
     * @param teamLeaderId The id for the new team leader.
     * @return true if the leader was changed successfully, false otherwise.
     */
    @RequestMapping(value = "/{id}/teamLeader", method = RequestMethod.POST)
    public @ResponseBody boolean changeTeamLeader(
            @PathVariable int id,
            @RequestParam(value = "teamLeaderdId") int teamLeaderId) {
        TeamDao teamDao = new TeamDao();
        Team team = teamDao.getTeamById(id, false, true);

        PlayerDao playerDao = new PlayerDao();
        Player teamLeader = playerDao.getPlayerById(teamLeaderId);

        if(team == null || teamLeader == null) {
            return false;
        }

        team.setTeamLeader(teamLeader);
        teamDao.saveTeam(team);
        return true;
    }

    /**
     * Adds the player given by playerId to the team given by id.
     * 
     * POST request data: playerId
     * 
     * @param id The id of the team.
     * @param playerId The id of the player to add.
     * @return true if the player was successfully added to the team, false
     *         otherwise.
     */
    // TODO add player ret val
    @RequestMapping(value = "/{id}/addPlayer", method = RequestMethod.POST)
    public @ResponseBody boolean addPlayerToTeam(
            @PathVariable int id,
            @RequestParam(value = "playerId") int playerId) {
        TeamDao teamDao = new TeamDao();
        Team team = teamDao.getTeamById(id, false, true);

        if(team == null) {
            return false;
        }

        PlayerDao playerDao = new PlayerDao();
        Player player = playerDao.getPlayerById(playerId);
        team.addPlayer(player);
        teamDao.saveTeam(team);
        return true;
    }

    /**
     * Removes the player given by playerId form the team given by id.
     * 
     * POST request data: playerId
     * 
     * @param id The id of the team.
     * @param playerId The id of the player to remove.
     * @return
     */
    // TODO remove player ret val
    @RequestMapping(value = "/{id}/removePlayer", method = RequestMethod.POST)
    public @ResponseBody boolean removePlayerFromTeam(
            @PathVariable int id,
            @RequestParam(value = "playerId") int playerId) {
        TeamDao teamDao = new TeamDao();
        Team team = teamDao.getTeamById(id, false, true);

        if(team == null) {
            return false;
        }

        PlayerDao playerDao = new PlayerDao();
        Player player = playerDao.getPlayerById(playerId);
        team.removePlayer(player);
        teamDao.saveTeam(team);
        return true;
    }
}
