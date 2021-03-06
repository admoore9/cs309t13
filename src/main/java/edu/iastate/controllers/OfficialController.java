/**
 * 
 */
package edu.iastate.controllers;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.iastate.dao.MemberDao;
import edu.iastate.dao.TournamentDao;
import edu.iastate.models.Game;
import edu.iastate.models.Member;
import edu.iastate.models.Member.UserType;
import edu.iastate.models.Team;
import edu.iastate.models.Tournament;

/**
 * All information need to be seen and used by the game coordinator
 * 
 * @author Shubang
 *
 */

@Controller
@RequestMapping(value = "/official")
public class OfficialController {

    /**
     * Returns the page for officials.
     * 
     * @param model The model for the jsp page.
     * @param session The http session for the current page.
     * @return The officials jsp page.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getTournament(Model model, HttpSession session) {

        Member member = (Member) session.getAttribute("member");
        MemberDao memberDao = new MemberDao();
        if (member == null || member.getContext() != UserType.OFFICIAL) {
            return "redirect:/denied";
        }
        member = memberDao.getMemberById(member.getId());
        if (member.getContext() != UserType.OFFICIAL) {
            return "redirect:/denied";
        }

        Set<Game> games = member.getGames();
        model.addAttribute("games", games);

        // For sidebar
        Set<Team> teams = member.getTeams();
        model.addAttribute("teams", teams);

        // For sidebar
        TournamentDao tournamentDao = new TournamentDao();
        List<Tournament> tournaments = tournamentDao.getLastXTournaments(5);
        model.addAttribute("tournaments", tournaments);

        session.setAttribute("member", memberDao.getMemberById(member.getId()));
        return "official";
    }
}
