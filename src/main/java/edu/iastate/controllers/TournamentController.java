package edu.iastate.controllers;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.iastate.dao.GameDao;
import edu.iastate.dao.MemberDao;
import edu.iastate.dao.MessageDao;
import edu.iastate.dao.TeamDao;
import edu.iastate.dao.TournamentDao;
import edu.iastate.models.Member;
import edu.iastate.models.Team;
import edu.iastate.models.Tournament;
import edu.iastate.utils.MemberUtils;

@Controller
@RequestMapping("/tournament")
public class TournamentController {

    /**
     * Returns the view for the tournament page given by id
     * 
     * @param model The model for the view
     * @param session The http session.
     * @param id The id of the tournament
     * @return The jsp page for the view.
     */
    @RequestMapping(value = "/{id}/view", method = RequestMethod.GET)
    public String viewTournament(Model model, HttpSession session, @PathVariable int id) {

        Member member = (Member) session.getAttribute("member");
        MemberDao memberDao = new MemberDao();
        member = memberDao.getMemberById(member.getId());
        if (member == null) {
            return "redirect:/denied";
        }

        TournamentDao tournamentDao = new TournamentDao();
        Tournament tournament = tournamentDao.getTournamentById(id, true, true);
        if (tournament == null) {
            return "redirect:/denied";
        }

        model.addAttribute("tournament", tournament);
        model.addAttribute("userType", member.getUserType());

        // For sidebar
        Set<Team> teams = member.getTeams();
        model.addAttribute("teams", teams);

        // For sidebar
        List<Tournament> tournaments = tournamentDao.getLastXTournaments(5);
        model.addAttribute("tournaments", tournaments);

        return "tournament";
    }

    /**
     * Creates a tournament with the given parameters. Administrators and
     * Coordinators can create tournaments.
     * 
     * @param session The http session
     * @param name The name of the tournament
     * @param minPlayers The minimum number of players per team.
     * @param maxPlayers The maximum number of players per team.
     * @param teamsPerGame The number of teams per game.
     * @param officialsPerGame The number of officials per game.
     * @return true if the tournament was successfully created, false otherwise.
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody boolean createTournament(
            HttpSession session,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "minPlayersPerTeam") int minPlayers,
            @RequestParam(value = "maxPlayersPerTeam") int maxPlayers,
            @RequestParam(value = "teamsPerGame") int teamsPerGame,
            @RequestParam(value = "officialsPerGame") int officialsPerGame) {
        Member me = (Member) session.getAttribute("member");
        if (!MemberUtils.atLeastCoordinator(me)) {
            return false;
        }

        Tournament tournament = new Tournament();
        tournament.setName(name);
        tournament.setMinPlayers(minPlayers);
        tournament.setMaxPlayers(maxPlayers);
        tournament.setTeamsPerGame(teamsPerGame);
        tournament.setOfficialsPerGame(officialsPerGame);

        TournamentDao tournamentDao = new TournamentDao();
        tournamentDao.saveTournament(tournament);
        return true;
    }

    /**
     * Returns the tournament given by id as JSON. Have to be logged in to
     * access.
     * 
     * @param session The http session
     * @param id The id of the tournament.
     * @return JSON representation of the tournament given by id.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Tournament getTournamentById(
            HttpSession session,
            @PathVariable int id) {
        Member me = (Member) session.getAttribute("member");
        if (me == null) {
            return null;
        }

        TournamentDao tournamentDao = new TournamentDao();
        return tournamentDao.getTournamentById(id, true, true);
    }

    /**
     * Updates the tournament given by id with the given parameters.
     * Administrators and Coordinators can create tournaments.
     * 
     * @param session The http session
     * @param id The id of the tournament.
     * @param name The new name for the tournament.
     * @param minPlayers The new minimum players per team.
     * @param maxPlayers The new maximum players per team.
     * @param teamsPerGame The new number of teams per game.
     * @param officialsPerGame The new number of officials per game.
     * @return true if the tournament was successfully updated, false otherwise.
     */
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public @ResponseBody boolean updateTournament(
            HttpSession session,
            @PathVariable int id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "minPlayersPerTeam") int minPlayers,
            @RequestParam(value = "maxPlayersPerTeam") int maxPlayers,
            @RequestParam(value = "teamsPerGame") int teamsPerGame,
            @RequestParam(value = "officialsPerGame") int officialsPerGame) {
        Member me = (Member) session.getAttribute("member");
        if (!MemberUtils.atLeastCoordinator(me)) {
            return false;
        }

        TournamentDao tournamentDao = new TournamentDao();
        Tournament tournament = tournamentDao.getTournamentById(id, false, false);

        if (tournament == null) {
            return false;
        }

        tournament.setName(name);
        tournament.setMinPlayers(minPlayers);
        tournament.setMaxPlayers(maxPlayers);
        tournament.setTeamsPerGame(teamsPerGame);
        tournament.setOfficialsPerGame(officialsPerGame);

        tournamentDao.saveTournament(tournament);
        return true;
    }

    /**
     * Sets the name of the tournament given by id to the given name. Only
     * accessible for Admins and Coordinators.
     * 
     * POST request data: name
     * 
     * @param session The http session
     * @param id The id of the tournament.
     * @param name The name you wish to set the tournament to.
     * @return true if the name was successfully set, false otherwise.
     */
    @RequestMapping(value = "/{id}/name", method = RequestMethod.POST)
    public @ResponseBody boolean setTournamentName(
            HttpSession session,
            @PathVariable int id,
            @RequestParam(value = "name") String name) {
        Member me = (Member) session.getAttribute("member");
        if (!MemberUtils.atLeastCoordinator(me)) {
            return false;
        }

        TournamentDao tournamentDao = new TournamentDao();
        Tournament tournament = tournamentDao.getTournamentById(id, false, false);

        if (tournament == null) {
            return false;
        }

        tournament.setName(name);
        tournamentDao.saveTournament(tournament);
        return true;
    }

    /**
     * Sets the minimum number of players per team for the tournament given by
     * id to the given number. This can't be set if the bracket is already
     * formed for the tournament. Only accessible for Admins and Coordinators.
     * 
     * POST request data: minPlayers
     * 
     * @param session The http session
     * @param id The id of the tournament.
     * @param minPlayers The minimum number of players you wish to set for the
     *            tournament.
     * @return true if the minimum number of players was set, false otherwise
     */
    @RequestMapping(value = "/{id}/minPlayersPerTeam", method = RequestMethod.POST)
    public @ResponseBody boolean setMinPlayersPerTeam(
            HttpSession session,
            @PathVariable int id,
            @RequestParam(value = "minPlayers") int minPlayers) {
        Member me = (Member) session.getAttribute("member");
        if (!MemberUtils.atLeastCoordinator(me)) {
            return false;
        }

        TournamentDao tournamentDao = new TournamentDao();
        Tournament tournament = tournamentDao.getTournamentById(id, false, false);

        if (tournament == null) {
            return false;
        }

        tournament.setMinPlayers(minPlayers);
        tournamentDao.saveTournament(tournament);
        return true;
    }

    /**
     * Sets the maximum number of players per team for the tournament given by
     * id to the given number. This can't be set if the bracket is already
     * formed for the tournament. Only accessible for Admins and Coordinators.
     * 
     * POST request data: maxPlayers
     * 
     * @param session The http session
     * @param id The id of the tournament.
     * @param maxPlayers The maximum number of players you wish to set for the
     *            tournament.
     * @return true if the maximum number of players was set, false otherwise
     */
    @RequestMapping(value = "/{id}/maxPlayersPerTeam", method = RequestMethod.POST)
    public @ResponseBody boolean setMaxPlayersPerTeam(
            HttpSession session,
            @PathVariable int id,
            @RequestParam(value = "maxPlayers") int maxPlayers) {
        Member me = (Member) session.getAttribute("member");
        if (!MemberUtils.atLeastCoordinator(me)) {
            return false;
        }

        TournamentDao tournamentDao = new TournamentDao();
        Tournament tournament = tournamentDao.getTournamentById(id, false, false);

        if (tournament == null) {
            return false;
        }

        tournament.setMaxPlayers(maxPlayers);
        tournamentDao.saveTournament(tournament);
        return true;
    }

    /**
     * Sets whether the tournament given by id is a double elimination
     * tournament. This can't be set if the bracket is already formed for the
     * tournament. Only accessible for Admins and Coordinators.
     * 
     * POST request data: doubleElimination
     * 
     * @param session The http session
     * @param id The id of the tournament.
     * @param doubleElimination Whether the tournament is a double elimination
     *            tournament.
     * @return true if the tournament was set by doubleElimination, false
     *         otherwise
     */
    @RequestMapping(value = "/{id}/doubleElimination", method = RequestMethod.POST)
    public @ResponseBody boolean setDoubleElimination(
            HttpSession session,
            @PathVariable int id,
            @RequestParam(value = "doubleElimination") boolean doubleElimination) {
        Member me = (Member) session.getAttribute("member");
        if (!MemberUtils.atLeastCoordinator(me)) {
            return false;
        }

        TournamentDao tournamentDao = new TournamentDao();
        Tournament tournament = tournamentDao.getTournamentById(id, false, false);

        if (tournament == null) {
            return false;
        }

        tournament.setDoubleElimination(doubleElimination);
        tournamentDao.saveTournament(tournament);
        return true;
    }

    /**
     * Adds the given team to the given tournament. Only adds the team if the
     * tournament bracket isn't formed yet. Have to be logged in to add a team.
     * 
     * POST request data: teamId
     * 
     * @param session The http session
     * @param id The id of the tournament to add the team to.
     * @param teamId The id of the team to add
     * @return true if the team was added to the tournament, false otherwise
     */
    @RequestMapping(value = "/{id}/addTeam", method = RequestMethod.POST)
    public @ResponseBody boolean addTeamToTournament(
            HttpSession session,
            @PathVariable int id,
            @RequestParam(value = "teamName") String teamName,
            @RequestParam(value = "acceptFreeAgents") boolean acceptFreeAgents,
            @RequestParam(value = "teamLeaderId") int teamLeaderId) {
        Member me = (Member) session.getAttribute("member");
        if (me == null) {
            return false;
        }

        TournamentDao tournamentDao = new TournamentDao();
        Tournament tournament = tournamentDao.getTournamentById(id, false, true);

        Team team = new Team();
        team.setName(teamName);
        team.setAcceptFreeAgents(acceptFreeAgents);
        team.setTournament(tournament);
        team.setTeamLeader(me);

        TeamDao teamDao = new TeamDao();
        teamDao.saveTeam(team);

        tournament.addTeam(team);
        tournamentDao.saveTournament(tournament);
        return true;
    }

    /**
     * Removes the given team from the given tournament. Only removes the team
     * if the tournament bracket isn't formed yet. Must be the leader of the
     * team to remove the team.
     * 
     * POST request data: teamId
     * 
     * @param session The http session
     * @param id The id of the tournament to remove the team from.
     * @param teamId The id of the team to remove
     * @return true if the team was removed from the tournament, false otherwise
     */
    @RequestMapping(value = "/{id}/removeTeam", method = RequestMethod.POST)
    public @ResponseBody boolean removeTeamFromTournament(
            HttpSession session,
            @PathVariable int id,
            @RequestParam(value = "teamId") int teamId) {
        Member me = (Member) session.getAttribute("member");
        if (me == null) {
            return false;
        }

        TournamentDao tournamentDao = new TournamentDao();
        Tournament tournament = tournamentDao.getTournamentById(id, false, true);

        TeamDao teamDao = new TeamDao();
        Team team = teamDao.getTeamById(teamId, false, false, false);

        if (tournament == null || team == null || !me.equals(team.getTeamLeader())) {
            return false;
        }

        tournament.removeTeam(team);
        teamDao.deleteTeamById(teamId);

        // notify team members
        MessageDao messageDao = new MessageDao();
        for (Member player : team.getPlayers())
            messageDao.notify(player, team.getName() + " team was removed from " + tournament.getName());

        return true;
    }

    /**
     * Forms the tournament given by id. Must be an Administrator or Coordinator
     * to form a tournament.
     * 
     * @param session The http session.
     * @param id The id of the tournament.
     * @return true if the tournament was successfully formed, false otherwise.
     */
    @RequestMapping(value = "/{id}/form", method = RequestMethod.POST)
    public @ResponseBody boolean formTournamentById(
            HttpSession session,
            @PathVariable int id) {
        Member me = (Member) session.getAttribute("member");
        if (me == null || !MemberUtils.atLeastCoordinator(me)) {
            return false;
        }

        TournamentDao tournamentDao = new TournamentDao();
        Tournament tournament = tournamentDao.getTournamentById(id, true, true);
        tournament.formBracket(new GameDao());
        tournamentDao.saveTournament(tournament);
        return true;
    }
}
