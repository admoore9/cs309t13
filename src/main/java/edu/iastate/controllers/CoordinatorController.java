/**
 * 
 */
package edu.iastate.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.iastate.dao.TournamentDao;
import edu.iastate.models.Game;
import edu.iastate.models.Team;
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
    public String getTournament(Model model, HttpSession session) {

        if (session.getAttribute("member") == null) {
            return "redirect:denied";
        }

        Member member = (Member) session.getAttribute("member");

        if (member.getUserType() != Member.UserType.COORDINATOR) {
            return "redirect:denied";
        }

        TournamentDao tournamentDao = new TournamentDao();
        List<Tournament> tournaments = tournamentDao.getTournamentByCoordinator(member);
        List<Team> teams = tournaments.get(0).getTeams();
        List<Game> games = tournaments.get(0).getGames();
        model.addAttribute("tournaments", tournaments);
        //model.addAttribute("teams", teams);
        //model.addAttribute("games", games);
        return "coordinator";
    }
}
