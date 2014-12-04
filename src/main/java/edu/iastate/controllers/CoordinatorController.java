/**
 * 
 */
package edu.iastate.controllers;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.iastate.models.Member;
import edu.iastate.models.Tournament;
import edu.iastate.models.Member.UserType;

/**
 * All information need to be seen and used by the game coordinator
 * 
 * @author Shubang
 *
 */

@Controller
@RequestMapping(value = "/coordinator")
public class CoordinatorController {
    @RequestMapping(method = RequestMethod.GET)
    public String getTournament(Model model, HttpSession session) {

        Member member = (Member) session.getAttribute("member");
        if (member == null || member.getContext() != UserType.COORDINATOR) {
            return "redirect:denied";
        }

        Set<Tournament> tournaments = member.getManagingTournament();
        model.addAttribute("tournaments", tournaments);
        return "coordinator";
    }
}
