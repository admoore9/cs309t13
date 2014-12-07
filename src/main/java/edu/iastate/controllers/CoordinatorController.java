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
import edu.iastate.models.Member;
import edu.iastate.models.Team;
import edu.iastate.models.Tournament;
import edu.iastate.models.Member.UserType;

@Controller
@RequestMapping(value = "/coordinator")
public class CoordinatorController {

    /**
     * Returns the view for the coordinator page
     * 
     * @param model The model for the view
     * @param session The http session
     * @return The jsp page for the view
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getTournament(Model model, HttpSession session) {

        Member member = (Member) session.getAttribute("member");
        MemberDao memberDao = new MemberDao();
        member = memberDao.getMemberById(member.getId());
        if (member == null || member.getContext() != UserType.COORDINATOR) {
            return "redirect:/denied";
        }

        Set<Tournament> managingTournaments = member.getManagingTournament();
        model.addAttribute("managingTournaments", managingTournaments);

        // For sidebar
        Set<Team> teams = member.getTeams();
        model.addAttribute("teams", teams);

        // For sidebar
        TournamentDao tournamentDao = new TournamentDao();
        List<Tournament> tournaments = tournamentDao.getLastXTournaments(5);
        model.addAttribute("tournaments", tournaments);

        session.setAttribute("member", memberDao.getMemberById(member.getId()));
        return "coordinator";
    }
}
