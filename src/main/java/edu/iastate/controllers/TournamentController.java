package edu.iastate.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.iastate.dao.TournamentDao;
import edu.iastate.models.Tournament;

@Controller
@RequestMapping("/tournament")
public class TournamentController {

    @RequestMapping(method = RequestMethod.GET)
    public String getTournament(Model model, HttpSession session) {

        if (session.getAttribute("member") == null) {
            return "redirect:denied";
        }

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
}
