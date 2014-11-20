package edu.iastate.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.iastate.dao.MemberDao;
import edu.iastate.dao.TeamDao;
import edu.iastate.dao.TournamentDao;
import edu.iastate.models.Game;
import edu.iastate.models.Member;
import edu.iastate.models.Team;
import edu.iastate.models.Tournament;

@Controller
@RequestMapping("/team")
public class TeamController {

    @RequestMapping(value = "/{id}/view", method = RequestMethod.GET)
    public String viewTournament(Model model, HttpSession session, @PathVariable int id) {
        model.addAttribute("teamId", id);

        if (session.getAttribute("member") == null) {
            return "redirect:denied";
        }

        return "team";
    }

    // TODO Use correct tournament
    // TODO Add players to team
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody void createTeam(
            @RequestParam(value = "teamName") String teamName,
            @RequestParam(value = "invitedPlayerUsername") String invitedPlayerUsername,
            HttpSession session) {

        TournamentDao tournamentDao = new TournamentDao();
        TeamDao teamDao = new TeamDao();

        Tournament tournament = tournamentDao.getTournamentById(1, false, false);
        Team team = new Team();
        Member teamLeader = (Member) session.getAttribute("member");

        team.setTournament(tournament);
        team.setName(teamName);
        team.setTeamLeader(teamLeader);

        teamDao.saveTeam(team);
    }

    /**
     * Returns the team given by id as JSON.
     *
     * @param id The id of the team.
     * @return JSON representation of the team given by id.
     */
    // TODO check if valid team
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Team getTeamById(@PathVariable int id) {
        TeamDao teamDao = new TeamDao();
        return teamDao.getTeamById(id, false, true, false);
    }

    /**
     * Returns the games that the team given by id has taken part in as JSON.
     *
     * @param id The id of the team
     * @return JSON representation of the games team has been in.
     */
    // TODO check if valid team
    @RequestMapping(value = "/{id}/games", method = RequestMethod.GET)
    public @ResponseBody List<Game> getGamesByTeam(@PathVariable int id) {
        TeamDao teamDao = new TeamDao();
        return teamDao.getTeamById(id, true, false, false).getGames();
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
    // TODO check if valid team
    // TODO check if users permissions are right
    @RequestMapping(value = "/{id}/name", method = RequestMethod.POST)
    public @ResponseBody boolean setTeamName(
            @PathVariable int id,
            @RequestParam(value = "name") String name) {
        TeamDao teamDao = new TeamDao();
        Team team = teamDao.getTeamById(id, false, false, false);

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
    // TODO check if valid team
    // TODO check if users permissions are right
    @RequestMapping(value = "/{id}/acceptFreeAgents", method = RequestMethod.GET)
    public @ResponseBody boolean setAcceptFreeAgents(
            @PathVariable int id,
            @RequestParam(value = "acceptFreeAgents") boolean acceptFreeAgents) {
        TeamDao teamDao = new TeamDao();
        Team team = teamDao.getTeamById(id, false, false, false);

        team.setAcceptFreeAgents(acceptFreeAgents);
        teamDao.saveTeam(team);
        return true;
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
    // TODO check if valid team
    // TODO check if users permissions are right
    @RequestMapping(value = "/{id}/teamLeader", method = RequestMethod.POST)
    public @ResponseBody boolean changeTeamLeader(
            @PathVariable int id,
            @RequestParam(value = "teamLeaderdId") int teamLeaderId) {
        TeamDao teamDao = new TeamDao();
        Team team = teamDao.getTeamById(id, false, true, false);

        MemberDao memberDao = new MemberDao();
        Member teamLeader = memberDao.getMemberById(teamLeaderId);

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
        Team team = teamDao.getTeamById(id, false, true, false);

        MemberDao memberDao = new MemberDao();
        Member player = memberDao.getMemberById(playerId);
        team.addPlayer(player);
        teamDao.saveTeam(team);
        return true;
    }

    /**
     * Removes the player given by playerId from the team given by id.
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
        Team team = teamDao.getTeamById(id, false, true, false);

        MemberDao memberDao = new MemberDao();
        Member player = memberDao.getMemberById(playerId);
        team.removePlayer(player);
        teamDao.saveTeam(team);
        return true;
    }

    @RequestMapping(value = "/{id}/players", method = RequestMethod.GET)
    public @ResponseBody List<Member> getPlayersForTeam(@PathVariable int id) {
        TeamDao teamDao = new TeamDao();
        Team team = teamDao.getTeamById(id, false, true, false);
        for (Member player : team.getPlayers()) {
            // Causing circular references... Should actually fix that
            player.setSurveys(null);
            player.setTeams(null);
        }
        return team.getPlayers();
    }
}
