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
import edu.iastate.dao.MessageDao;
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
    public String viewTeam(
            Model model,
            HttpSession session,
            @PathVariable int id) {
        model.addAttribute("teamId", id);

        if (session.getAttribute("member") == null) {
            return "redirect:denied";
        }

        return "team";
    }

    @RequestMapping(value = "/{tournamentId}/create", method = RequestMethod.GET)
    public String createTeam(
            @PathVariable int tournamentId,
            Model model,
            HttpSession session) {

        if (session.getAttribute("member") == null) {
            return "redirect:denied";
        }

        model.addAttribute("tournamentId", tournamentId);

        return "createTeam";
    }

    // TODO Add players to team
    @RequestMapping(value = "/{tournamentId}/create/submit", method = RequestMethod.POST)
    public String createTeamSubmit(
            @PathVariable int tournamentId,
            @RequestParam(value = "teamName") String teamName,
            @RequestParam(value = "invitedPlayerUsername") String invitedPlayerUsername,
            HttpSession session) {

        TournamentDao tournamentDao = new TournamentDao();
        TeamDao teamDao = new TeamDao();
        MemberDao memberDao = new MemberDao();

        Tournament tournament = tournamentDao.getTournamentById(tournamentId, false, false);
        Team team = new Team();
        Member teamLeader = (Member) session.getAttribute("member");

        team.setTournament(tournament);
        team.setName(teamName);
        team.setTeamLeader(teamLeader);
        teamDao.saveTeam(team);

        teamLeader = memberDao.getMemberById(teamLeader.getId());
        session.setAttribute("member", teamLeader);

        return "redirect:../../../profile";
    }

    /**
     * Returns the team given by id as JSON.
     * 
     * @param session The http session
     * @param id The id of the team.
     * @return JSON representation of the team given by id.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Team getTeamById(
            HttpSession session,
            @PathVariable int id) {
        if (session.getAttribute("member") == null) {
            return null;
        }

        TeamDao teamDao = new TeamDao();
        return teamDao.getTeamById(id, false, true, false);
    }

    /**
     * Returns the games that the team given by id has taken part in as JSON.
     *
     * @param session The http session
     * @param id The id of the team
     * @return JSON representation of the games team has been in.
     */
    @RequestMapping(value = "/{id}/games", method = RequestMethod.GET)
    public @ResponseBody List<Game> getGamesByTeam(
            HttpSession session,
            @PathVariable int id) {
        if (session.getAttribute("member") == null) {
            return null;
        }

        TeamDao teamDao = new TeamDao();
        Team team = teamDao.getTeamById(id, true, false, false);
        return team == null ? null : team.getGames();
    }

    /**
     * Sets the name of the team given by id to the given name.
     * 
     * POST request data: name
     * 
     * @param session The http session
     * @param id The id of the team
     * @param name The new name for the team
     * @return true if the name was successfully changed, false otherwise.
     */
    @RequestMapping(value = "/{id}/name", method = RequestMethod.POST)
    public @ResponseBody boolean setTeamName(
            HttpSession session,
            @PathVariable int id,
            @RequestParam(value = "name") String name) {
        Member me = (Member) session.getAttribute("member");
        if (me == null) {
            return false;
        }

        TeamDao teamDao = new TeamDao();
        Team team = teamDao.getTeamById(id, false, false, false);

        if (team == null || !me.equals(team.getTeamLeader())) {
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
     * @params session The http session for the user.
     * @param id The id of the team.
     * @param acceptFreeAgents Whether the team should accept free agents.
     * @return true if acceptFreeAgents was successfully updated, false
     *         otherwise.
     */
    @RequestMapping(value = "/{id}/acceptFreeAgents", method = RequestMethod.GET)
    public @ResponseBody boolean setAcceptFreeAgents(
            HttpSession session,
            @PathVariable int id,
            @RequestParam(value = "acceptFreeAgents") boolean acceptFreeAgents) {
        Member me = (Member) session.getAttribute("member");
        if (me == null) {
            return false;
        }

        TeamDao teamDao = new TeamDao();
        Team team = teamDao.getTeamById(id, false, false, false);

        if (team == null || !me.equals(team.getTeamLeader())) {
            return false;
        }

        team.setAcceptFreeAgents(acceptFreeAgents);
        teamDao.saveTeam(team);
        return true;
    }

    /**
     * Changes the given teams leader to the player with the given id.
     * 
     * POST request data: teamLeaderId
     * 
     * @param session The http session for the current user.
     * @param id The id of the team.
     * @param teamLeaderId The id for the new team leader.
     * @return true if the leader was changed successfully, false otherwise.
     */
    @RequestMapping(value = "/{id}/teamLeader", method = RequestMethod.POST)
    public @ResponseBody boolean changeTeamLeader(
            HttpSession session,
            @PathVariable int id,
            @RequestParam(value = "teamLeaderdId") int teamLeaderId) {
        Member me = (Member) session.getAttribute("member");
        if (me == null) {
            return false;
        }

        TeamDao teamDao = new TeamDao();
        Team team = teamDao.getTeamById(id, false, true, false);

        if (team == null || !me.equals(team.getTeamLeader())) {
            return false;
        }

        MemberDao memberDao = new MemberDao();
        Member teamLeader = memberDao.getMemberById(teamLeaderId);

        team.setTeamLeader(teamLeader);
        // notify member of being assigned as team leader
        new MessageDao().notify(teamLeader, "You've been assigned as " + team.getName() + " leader");

        teamDao.saveTeam(team);
        return true;
    }

    /**
     * Adds the player given by playerId to the team given by id.
     * 
     * POST request data: playerId
     * 
     * @param session The http session for the user.
     * @param id The id of the team.
     * @param playerId The id of the player to add.
     * @return true if the player was successfully added to the team, false
     *         otherwise.
     */
    @RequestMapping(value = "/{id}/addPlayer", method = RequestMethod.POST)
    public @ResponseBody boolean addPlayerToTeam(
            HttpSession session,
            @PathVariable int id,
            @RequestParam(value = "playerId") int playerId) {
        Member me = (Member) session.getAttribute("member");
        if (me == null) {
            return false;
        }

        TeamDao teamDao = new TeamDao();
        Team team = teamDao.getTeamById(id, false, true, false);

        if (team == null || me.equals(team.getTeamLeader())) {
            return false;
        }

        MemberDao memberDao = new MemberDao();
        Member player = memberDao.getMemberById(playerId);
        team.addPlayer(player);
        // notify member of being assigned as team leader
        new MessageDao().notify(player, "You've been added to " + team.getName() + " team");

        teamDao.saveTeam(team);
        return true;
    }

    /**
     * Removes the player given by playerId from the team given by id.
     * 
     * POST request data: playerId
     * 
     * @param session The http session for the user.
     * @param id The id of the team.
     * @param playerId The id of the player to remove.
     * @return
     */
    @RequestMapping(value = "/{id}/removePlayer", method = RequestMethod.POST)
    public @ResponseBody boolean removePlayerFromTeam(
            HttpSession session,
            @PathVariable int id,
            @RequestParam(value = "playerId") int playerId) {
        Member me = (Member) session.getAttribute("member");
        if (me == null) {
            return false;
        }

        TeamDao teamDao = new TeamDao();
        Team team = teamDao.getTeamById(id, false, true, false);

        if (team == null || me.equals(team.getTeamLeader())) {
            return false;
        }

        MemberDao memberDao = new MemberDao();
        Member player = memberDao.getMemberById(playerId);
        team.removePlayer(player);
        // notify member of being assigned as team leader
        new MessageDao().notify(player, "You've been removed from " + team.getName() + " team");

        teamDao.saveTeam(team);
        return true;
    }

    /**
     * Gets the players on a team
     * 
     * @param session The http session for the user.
     * @param id The id of the team
     * @return The players on the team identified by id
     */
    @RequestMapping(value = "/{id}/players", method = RequestMethod.GET)
    public @ResponseBody List<Member> getPlayersForTeam(
            HttpSession session,
            @PathVariable int id) {
        if (session.getAttribute("member") == null) {
            return null;
        }

        TeamDao teamDao = new TeamDao();
        Team team = teamDao.getTeamById(id, false, true, false);

        return team.getPlayers();
    }
}
