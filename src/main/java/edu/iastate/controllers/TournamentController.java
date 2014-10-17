package edu.iastate.controllers;

import edu.iastate.dao.TournamentDao;
import edu.iastate.models.Game;
import edu.iastate.models.Tournament;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

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

    @RequestMapping(value = "/{id}/get_rounds", method = RequestMethod.GET)
    public @ResponseBody Map<Integer, List<Game>> getRounds(@PathVariable int id) {
        TournamentDao tournamentDao = new TournamentDao();
        Tournament tournament = tournamentDao.getTournamentById(id, true, false);
        Map<Integer, List<Game>> map = new HashMap<Integer, List<Game>>();
        for(Game game : tournament.getGames()) {
            int roundNumber = game.getRoundNumber();
            if(map.get(roundNumber) != null) {
                map.get(roundNumber).add(game);
            } else {
                map.put(roundNumber, new ArrayList<Game>());
                map.get(roundNumber).add(game);
            }
        }

        return map;
    }

}
