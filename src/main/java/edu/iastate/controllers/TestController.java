package edu.iastate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.iastate.dao.TournamentDao;
import edu.iastate.models.Game;
import edu.iastate.models.Tournament;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping(method = RequestMethod.GET)
    public String test(Model model) {
        model.addAttribute("name", "something put in in the controller");

        TournamentDao tournamentDao = new TournamentDao();
        Tournament t = tournamentDao.getTournamentById(1, true, false);
        for(Game g : t.getGames()) {
            System.out.println(g.getGameLocation());
        }

        return "test";
    }
}
