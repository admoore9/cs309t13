package edu.iastate.controllers;

import java.util.Iterator;
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
import edu.iastate.dao.TeamDao;
import edu.iastate.dao.TournamentDao;
import edu.iastate.models.Game;
import edu.iastate.models.Member;
import edu.iastate.models.Team;
import edu.iastate.models.Tournament;
import edu.iastate.utils.MemberUtils;
import edu.iastate.utils.StringUtils;

@Controller
@RequestMapping("/team")
public class TeamController {

    private static final String JOIN_TEAM_SUCCESS_MESSAGE = "Join complete!";
    private static final String JOIN_TEAM_ERROR_MESSAGE = "Team Password Incorrect!";

    @RequestMapping(value = "/{id}/view", method = RequestMethod.GET)
    public String viewTeam(Model model, HttpSession session, @PathVariable int id) {
        model.addAttribute("teamId", id);

        Member member = (Member) session.getAttribute("member");
        if (member == null) {
            return "redirect:/denied";
        }

        TeamDao teamDao = new TeamDao();
        Team team = teamDao.getTeamById(id, true, true, true);

        if (member.getTeams().contains(team) == false) {
            return "redirect:/denied";
        }

        Set<Team> teams = member.getTeams();
        model.addAttribute("teams", teams);

        TournamentDao tournamentDao = new TournamentDao();
        List<Tournament> tournaments = tournamentDao.getLastXTournaments(5);
        model.addAttribute("tournaments", tournaments);

        model.addAttribute("team", team);
        MemberDao memberDao = new MemberDao();
        session.setAttribute("member", memberDao.getMemberById(member.getId()));
        return "team";
    }

    /**
     * Checks if team name already exists in database
     * @param teamName the name to check in database
     * @return true if team name is available, false otherwise
     */
    @RequestMapping(value = "/{tournamentId}/available", method = RequestMethod.GET)
    public @ResponseBody String isTeamNameAvailable(
            @PathVariable int tournamentId,
            @RequestParam(value = "teamName") String teamName) {
        TeamDao teamDao = new TeamDao();
        TournamentDao tournamentDao = new TournamentDao();
        Team team = teamDao.getTeamByTeamName(teamName, tournamentDao.getTournamentById(tournamentId, true, true));
        String isValid;
        if (team == null) {
            isValid = "{ \"valid\": true }";
        }
        else {
            isValid = "{ \"valid\": false }";
        }
        return isValid;
    }

    /**
     * Updates the team with various parameters 
     * 
     * @param id the game ID
     * @param The new name of team
     * @param addPlayer the username of player to add
     * @param removePlayer the username of player to remove
     * @param newCaptain the username of new captain
     * @return true if successful
     */
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String updateTeam(
            @PathVariable int id,
            @RequestParam(value = "teamName", required = false) String teamName,
            @RequestParam(value = "addPlayer", required = false) String addPlayer,
            @RequestParam(value = "removePlayer", required = false) String removePlayer,
            @RequestParam(value = "newCaptain", required = false) String newCaptain,
            HttpSession session,
            Model model) {

        TeamDao teamDao = new TeamDao();
        MemberDao memberDao = new MemberDao();
        Team team = teamDao.getTeamById(id, true, true, true);

        // Validates the user permission
        Member member = (Member) session.getAttribute("member");
        if (!team.getTeamLeader().equals(member))
            return "redirect:/denied";

        if (teamName != null && teamName.length() != 0)
            team.setName(teamName);

        if (removePlayer != null && removePlayer.length() != 0)
            team.removePlayer(memberDao.getMemberByUsername(removePlayer));

        if (addPlayer != null && addPlayer.length() != 0)
            team.addInvitedPlayer(memberDao.getMemberByUsername(addPlayer));

        if (newCaptain != null && newCaptain.length() != 0) {
            Member teamLeader = memberDao.getMemberByUsername(newCaptain);
            if (teamLeader != null) {
                if (team.getPlayers().contains(teamLeader)) {
                    team.setTeamLeader(teamLeader);
                }
            }
        }

        Set<Team> teams = member.getTeams();
        model.addAttribute("teams", teams);

        teamDao.saveTeam(team);
        model.addAttribute("team", team);

        memberDao.save(member);
        session.setAttribute("member", memberDao.getMemberById(member.getId()));

        return "team";
    }

    @RequestMapping(value = "/{tournamentId}/create", method = RequestMethod.GET)
    public String createTeam(@PathVariable int tournamentId, Model model, HttpSession session) {

        Member member = (Member) session.getAttribute("member");

        if (member == null) {
            return "redirect:/denied";
        }

        Set<Team> teams = member.getTeams();

        model.addAttribute("teams", teams);

        TournamentDao tournamentDao = new TournamentDao();
        List<Tournament> tournaments = tournamentDao.getLastXTournaments(5);
        model.addAttribute("tournaments", tournaments);

        model.addAttribute("tournamentId", tournamentId);

        return "createTeam";
    }

    // TODO Add players to team
    @RequestMapping(value = "/{tournamentId}/create/submit", method = RequestMethod.POST)
    public String createTeamSubmit(
            @PathVariable int tournamentId,
            @RequestParam(value = "teamName") String teamName,
            @RequestParam(value = "teamPassword") String teamPassword,
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
        
        String genPassword = StringUtils.secureString(teamPassword);
        
        team.setPassword(genPassword);

        teamDao.saveTeam(team);

        teamLeader = memberDao.getMemberById(teamLeader.getId());
        session.setAttribute("member", teamLeader);

        int teamId = teamDao.getTeamByTeamName(teamName, tournament).getId();
        
        Set<Team> invitedTeams = teamLeader.getInvitedTeams();
        Iterator<Team> teamIterator = invitedTeams.iterator();
        while(teamIterator.hasNext()) {
            Team invitedTeam = teamIterator.next();
            if(invitedTeam.getTournament().equals(team.getTournament())){
                invitedTeam.removeInvitedPlayer(teamLeader);
                teamDao.saveTeam(invitedTeam);
            }
        }
        return "redirect:/team/" + teamId + "/view";
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
    public @ResponseBody Set<Game> getGamesByTeam(
            HttpSession session,
            @PathVariable int id) {
        if (session.getAttribute("member") == null) {
            return null;
        }

        TeamDao teamDao = new TeamDao();
        Team team = teamDao.getTeamById(id, true, false, false);
        return (team == null ? null : team.getGames());
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
            @PathVariable int id) {
        Member me = (Member) session.getAttribute("member");
        if (me == null) {
            return false;
        }

        TeamDao teamDao = new TeamDao();
        Team team = teamDao.getTeamById(id, false, true, false);

        if (team == null || me.equals(team.getTeamLeader())) {
            return false;
        }

        if(team.addPlayer(me)==1) {
            teamDao.saveTeam(team);
            Set<Team> invitedTeams = me.getInvitedTeams();
            Iterator<Team> teamIterator = invitedTeams.iterator();
            while(teamIterator.hasNext()) {
                Team invitedTeam = teamIterator.next();
                if(invitedTeam.getTournament().equals(team.getTournament())){
                    invitedTeam.removeInvitedPlayer(me);
                    teamDao.saveTeam(invitedTeam);
                }
            }
        }
        return true;
    }

    /**
     * Called when player wants to join team
     * Checks for team password for security
     * @param session
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/joinTeam", method = RequestMethod.POST)
    public @ResponseBody boolean joinPlayerToTeam(
            @RequestParam(value = "teamPassword", required = true) String enteredPassword,
            @PathVariable int id,
            HttpSession session) {
        Member me = (Member) session.getAttribute("member");
        if (me == null) {
            return false;
        }

        TeamDao teamDao = new TeamDao();
        Team team = teamDao.getTeamById(id, false, true, false);
        String teamPassword = team.getPassword();
        
        String genPassword = StringUtils.secureString(enteredPassword);

        if (team == null || !genPassword.equals(teamPassword)) {
            return false;
        }

        if(team.addPlayer(me)==1) {
            teamDao.saveTeam(team);
            Set<Team> invitedTeams = me.getInvitedTeams();
            Iterator<Team> teamIterator = invitedTeams.iterator();
            while(teamIterator.hasNext()) {
                Team invitedTeam = teamIterator.next();
                if(invitedTeam.getTournament().equals(team.getTournament())){
                    invitedTeam.removeInvitedPlayer(me);
                    teamDao.saveTeam(invitedTeam);
                }
            }
        }

        return true;
    }

    /**
     * Called when player rejects an invitation to join team
     * 
     * @param session
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/rejectInvite", method = RequestMethod.POST)
    public @ResponseBody boolean removePlayerFromInvitedTeam(
            HttpSession session,
            @PathVariable int id) {
        Member me = (Member) session.getAttribute("member");
        if (me == null) {
            return false;
        }

        TeamDao teamDao = new TeamDao();
        Team team = teamDao.getTeamById(id, false, true, false);

        if (team == null || me.equals(team.getTeamLeader())) {
            return false;
        }

        team.removeInvitedPlayer(me);
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
    public @ResponseBody Set<Member> getPlayersForTeam(
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
