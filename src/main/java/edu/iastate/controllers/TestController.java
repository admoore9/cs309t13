package edu.iastate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.iastate.dao.TournamentDao;
import edu.iastate.models.Tournament;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping(method = RequestMethod.GET)
    public String test(Model model) {
        model.addAttribute("name", "something put in in the controller");

        TournamentDao tournamentDao = TournamentDao.getInstance();
        Tournament t = tournamentDao.getTournamentById(1);
        System.out.println(t.toString());
        return "test";
    }
}
