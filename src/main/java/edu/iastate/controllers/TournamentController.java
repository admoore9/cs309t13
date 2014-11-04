package edu.iastate.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.iastate.dao.TeamDao;
import edu.iastate.dao.TournamentDao;
import edu.iastate.models.Team;
import edu.iastate.models.Tournament;

@Controller
@RequestMapping("/tournament")
public class TournamentController {

    @RequestMapping(method = RequestMethod.GET)
    public String getTournament(Model model) {
        TournamentDao tournamentDao = new TournamentDao();
        List<Tournament> tournaments = tournamentDao.getLastXTournaments(5);
        model.addAttribute("tournaments", tournaments);
        return "tournament";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Tournament getTournamentById(@PathVariable int id) {
        TournamentDao tournamentDao = new TournamentDao();
        Tournament tournament = tournamentDao.getTournamentById(id, true, true);
        return tournament;
    }

    @RequestMapping(value = "/{id}/addTeam", method = RequestMethod.POST)
    public @ResponseBody void addTeamToTournament(@PathVariable int id, @RequestParam(value = "teamId") int teamId) {
        TournamentDao tournamentDao = new TournamentDao();
        Tournament tournament = tournamentDao.getTournamentById(id, false, true);
        TeamDao teamDao = new TeamDao();
        Team team = teamDao.getTeamById(teamId, false, false);
        tournament.addTeam(team);
        tournamentDao.saveTournament(tournament);
        teamDao.saveTeam(team);
    }

    @RequestMapping(value = "/{id}/removeTeam", method = RequestMethod.POST)
    public @ResponseBody void removeTeamFromTournament(@PathVariable int id, @RequestParam(value = "teamId") int teamId) {
        TournamentDao tournamentDao = new TournamentDao();
        Tournament tournament = tournamentDao.getTournamentById(id, false, true);
        TeamDao teamDao = new TeamDao();
        Team team = teamDao.getTeamById(teamId, false, false);
        tournament.removeTeam(team);
        tournamentDao.saveTournament(tournament);
        teamDao.saveTeam(team);
    }

    @RequestMapping(value = "/{id}/teams", method = RequestMethod.GET)
    public @ResponseBody List<Team> getTeamsForTournament(@PathVariable int id) {
        TournamentDao tournamentDao = new TournamentDao();
        Tournament tournament = tournamentDao.getTournamentById(id, false, true);
        return tournament.getTeams();
    }

    // GET url: ‘tournament/{id}’ data: {} Displays an html page with the
    // details of the tournament.
    // POST url: ‘tournament/{id}/form’ data: {} Forms the tournament given by
    // id
    // POST url: ‘tournament/create’ data: {name, minPlayersPerTeam,
    // maxPlayersPerTeam, teamsPerGame, officialsPerGame} Creates a tournament
    // with the given parameters, returns id of created tournament
    // POST url: ‘tournament/{id}/addTeam’ data: {teamId} Adds the team to the
    // tournament
    // POST url: ‘tournament/{id}/removeTeam’ data: {teamId} Removes the team
    // from the tournament
    // POST url: ‘tournament/{id}/start’ data: {} Starts the given tournament.
    // GET url: ‘tournament/{id}/teams’ data: {} Returns the teams in the
    // tournament
    //
    // POST url: ‘team/create’ data: {} Create a team, returns id of created
    // team.
    // GET url: ‘team/{id}/players’ data: {} Returns the players on the team
    // POST url: ‘team/{id}/addPlayer’ data: {playerId} Adds the player to the
    // team.
    // POST url: ‘team/{id}/removePlayer’ data: {playerId} Removes the player
    // from the team
    //
    // GET url: ‘game/{id}/teams’ data: {} Returns the teams for a game
    // GET url: ‘game/{id}/winner’ data: {} Returns the winning team for a game
    // POST url: ‘game/{id}/winner’ data: {teamId} Sets the winner of the game
    //
    // POST url: ‘member/create’ data: {username, password, usertype} Create the
    // member
    // GET url: ‘member/{id}’ data: {} Returns the details of the user
    //
    // POST url: ‘survey/submit’ data: {sex, height, weight, compYears,
    // intsPlayed, compLvl, isClubPlayer} Submits the survey

}
