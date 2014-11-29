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
import edu.iastate.models.Team;
import edu.iastate.models.Tournament;
import edu.iastate.utils.StringUtils;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private static final String NAME_ERROR_MESSAGE = "Your name is invalid and was not saved.";
    private static final String PASSWORD_ERROR_MESSAGE = "Your password is invalid and was not saved.";
    private static final String SEX_ERROR_MESSAGE = "Your sex is invalid and was not saved.";
    private static final String HEIGHT_ERROR_MESSAGE = "Your height is invalid and was not saved.";
    private static final String WEIGHT_ERROR_MESSAGE = "Your weight is invalid and was not saved.";

    @RequestMapping(method = RequestMethod.GET)
    public String loadProfilePage(Model model, HttpSession session) {

        if (session.getAttribute("member") == null) {
            return "redirect:denied";
        }

        Member member = (Member) session.getAttribute("member");

        List<Team> teams = member.getTeams();
        model.addAttribute("teams", teams);

        TournamentDao tournamentDao = new TournamentDao();
        List<Tournament> tournaments = tournamentDao.getLastXTournaments(5);
        model.addAttribute("tournaments", tournaments);

        return "profile";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editProfileSubmit(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "sex", required = false) String sex,
            @RequestParam(value = "height", required = false) Integer height,
            @RequestParam(value = "weight", required = false) Integer weight,
            Model model,
            HttpSession session) {

        MemberDao memberDao = new MemberDao();
        Member member = (Member) session.getAttribute("member");

        List<Team> teams = member.getTeams();
        model.addAttribute("teams", teams);

        TournamentDao tournamentDao = new TournamentDao();
        List<Tournament> tournaments = tournamentDao.getLastXTournaments(5);
        model.addAttribute("tournaments", tournaments);

        if (name != null && name.length() != 0) {
            if (name.length() <= 45) {
                member.setName(name);
            } else {
                model.addAttribute("errorMessage", NAME_ERROR_MESSAGE);
                return "profile";
            }
        }

        if (password != null && password.length() != 0) {
            if (password.length() <= 45) {
                String genPassword = StringUtils.secureString(password);
                member.setPassword(genPassword);
            } else {
                model.addAttribute("errorMessage", PASSWORD_ERROR_MESSAGE);
                return "profile";
            }
        }

        if (sex != null) {
            if (sex.length() == 1) {
                member.setSex(sex);
            } else {
                model.addAttribute("errorMessage", SEX_ERROR_MESSAGE);
                return "profile";
            }
        }

        if (height != null) {
            if (height >= 0 && height <= 100) {
                member.setHeight(height);
            } else {
                model.addAttribute("errorMessage", HEIGHT_ERROR_MESSAGE);
                return "profile";
            }
        }

        if (weight != null) {
            if (weight >= 0 && weight <= 1000) {
                member.setWeight(weight);
            } else {
                model.addAttribute("errorMessage", WEIGHT_ERROR_MESSAGE);
                return "profile";
            }
        }

        memberDao.save(member);
        session.setAttribute("member", member);

        return "profile";
    }
}
