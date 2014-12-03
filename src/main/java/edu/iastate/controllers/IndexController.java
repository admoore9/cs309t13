package edu.iastate.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.iastate.dao.MemberDao;
import edu.iastate.dao.TournamentDao;
import edu.iastate.models.Member;
import edu.iastate.models.Tournament;
import edu.iastate.utils.StringUtils;

@Controller
@RequestMapping("/")
public class IndexController {

    private static final String LOGIN_SUCCESS_MESSAGE = "Login complete!";
    private static final String LOGIN_ERROR_MESSAGE = "Your login was invalid, please try again.";

    @RequestMapping(method = RequestMethod.GET)
    public String loadIndexPage(Model model) {

        TournamentDao tournamentDao = new TournamentDao();
        List<Tournament> tournaments = tournamentDao.getLastXTournaments(5);
        model.addAttribute("tournaments", tournaments);
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            HttpSession session,
            Model model) {

        TournamentDao tournamentDao = new TournamentDao();
        MemberDao memberDao = new MemberDao();

        List<Tournament> tournaments = tournamentDao.getLastXTournaments(5);
        model.addAttribute("tournaments", tournaments);

        String genPassword = StringUtils.secureString(password);

        Member member = memberDao.getMemberByUsernamePassword(username, genPassword);

        if (member != null) {
            session.setAttribute("member", member);
            model.addAttribute("message", LOGIN_SUCCESS_MESSAGE);
        } else {
            model.addAttribute("errorMessage", LOGIN_ERROR_MESSAGE);
        }
        return "redirect:profile";
    }
}
