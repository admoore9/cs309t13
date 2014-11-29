/**
 * 
 */
package edu.iastate.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.iastate.dao.TournamentDao;
import edu.iastate.models.Tournament;
import edu.iastate.dao.MemberDao;
import edu.iastate.models.Member;
/**
 * @author Shubang
 *
 */

@Controller
@RequestMapping(value = "/coordinator")
public class CoordinatorController {
    @RequestMapping(method = RequestMethod.GET)
    public String getTournament(Model model) {
        TournamentDao tournamentDao = new TournamentDao();
        List<Tournament> tournaments = tournamentDao.getLastXTournaments(5);
        model.addAttribute("tournaments", tournaments);
        return "admin";
    }
}
