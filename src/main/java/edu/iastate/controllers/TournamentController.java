package edu.iastate.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.iastate.dao.GameDao;
import edu.iastate.dao.TournamentDao;
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

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody void createTournament(@RequestParam(value = "name") String name,
            @RequestParam(value = "minPlayersPerTeam") int minPlayers,
            @RequestParam(value = "maxPlayersPerTeam") int maxPlayers,
            @RequestParam(value = "teamsPerGame") int teamsPerGame,
            @RequestParam(value = "officialsPerGame") int officialsPerGame) {
        Tournament tournament = new Tournament();
        tournament.setName(name);
        tournament.setMinPlayers(minPlayers);
        tournament.setMaxPlayers(maxPlayers);
        tournament.setTeamsPerGame(teamsPerGame);
        tournament.setOfficialsPerGame(officialsPerGame);

        TournamentDao tournamentDao = new TournamentDao();
        tournamentDao.saveTournament(tournament);
    }

    @RequestMapping(value = "/{id}/form", method = RequestMethod.POST)
    public @ResponseBody void formTournamentById(@PathVariable int id) {
        TournamentDao tournamentDao = new TournamentDao();
        Tournament tournament = tournamentDao.getTournamentById(id, true, true);
        tournament.formBracket(new GameDao());
        tournamentDao.saveTournament(tournament);
    }
}
