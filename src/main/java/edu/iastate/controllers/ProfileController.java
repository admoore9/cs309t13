package edu.iastate.controllers;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.iastate.dao.MemberDao;
import edu.iastate.dao.MessageDao;
import edu.iastate.dao.TournamentDao;
import edu.iastate.models.Member;
import edu.iastate.models.Member.UserType;
import edu.iastate.models.Team;
import edu.iastate.models.Tournament;
import edu.iastate.utils.MemberUtils;
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

        Member member = (Member) session.getAttribute("member");
        MemberDao memberDao = new MemberDao();
        member = memberDao.getMemberById(member.getId());
        if (member == null || member.getContext() != UserType.PLAYER) {
            return "redirect:/denied";
        }

        Set<Team> invitedTeams = member.getInvitedTeams();
        model.addAttribute("invitedTeams", invitedTeams);
        model.addAttribute("invitedTeamsSize", invitedTeams.size());

        // For sidebar
        Set<Team> teams = member.getTeams();
        model.addAttribute("teams", teams);

        // For sidebar
        TournamentDao tournamentDao = new TournamentDao();
        List<Tournament> tournaments = tournamentDao.getLastXTournaments(5);
        model.addAttribute("tournaments", tournaments);

        session.setAttribute("member", memberDao.getMemberById(member.getId()));
        return "profile";
    }

    @RequestMapping(value = "/promote", method = RequestMethod.POST)
    public @ResponseBody boolean promote(
            HttpSession session,
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "newUserType", required = true) int newUserTypeInt) {

        Member me = (Member) session.getAttribute("member");
        if (me == null || !MemberUtils.atLeastCoordinator(me)) {
            return false;
        }

        MemberDao memberDao = new MemberDao();
        Member promotee = memberDao.getMemberByUsername(username);

        Member.UserType newUserType = Member.UserType.values()[newUserTypeInt];

        if (me.getUserType() == Member.UserType.COORDINATOR &&
                (MemberUtils.atLeastCoordinator(promotee) ||
                (newUserType == Member.UserType.ADMIN || newUserType == Member.UserType.COORDINATOR))) {
            return false;
        }

        String message = "Your user type has been changed to " + newUserType + " by " + me.getName() + ".";
        new MessageDao().notify(promotee, message);

        promotee.setUserType(newUserType);
        promotee.setContext(newUserType);
        memberDao.save(promotee);

        return true;
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

        Set<Team> teams = member.getTeams();
        model.addAttribute("teams", teams);

        TournamentDao tournamentDao = new TournamentDao();
        List<Tournament> tournaments = tournamentDao.getLastXTournaments(5);
        model.addAttribute("tournaments", tournaments);

        String errorMessage = "";

        if (name != null && name.length() != 0) {
            if (name.length() <= 45) {
                member.setName(name);
            } else {
                errorMessage = errorMessage + " " + NAME_ERROR_MESSAGE;
            }
        }

        if (password != null && password.length() != 0) {
            if (password.length() <= 45) {
                String genPassword = StringUtils.secureString(password);
                member.setPassword(genPassword);
            } else {
                errorMessage = errorMessage + " " + PASSWORD_ERROR_MESSAGE;
            }
        }

        if (sex != null) {
            if (sex.equals("m") || sex.equals("f")) {
                member.setSex(sex);
            } else {
                errorMessage = errorMessage + " " + SEX_ERROR_MESSAGE;
            }
        }

        if (height != null) {
            if (height >= 0 && height <= 100) {
                member.setHeight(height);
            } else {
                errorMessage = errorMessage + " " + HEIGHT_ERROR_MESSAGE;
            }
        }

        if (weight != null) {
            if (weight >= 0 && weight <= 1000) {
                member.setWeight(weight);
            } else {
                errorMessage = errorMessage + " " + WEIGHT_ERROR_MESSAGE;
            }
        }

        memberDao.save(member);
        if (!errorMessage.equals("")) {
            System.out.println(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
        } else {
            model.addAttribute("message", "Profile updated.");
        }

        session.setAttribute("member", memberDao.getMemberById(member.getId()));

        return "profile";
    }
}
