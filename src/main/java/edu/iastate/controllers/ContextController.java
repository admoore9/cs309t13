package edu.iastate.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.iastate.dao.MemberDao;
import edu.iastate.models.Member;
import edu.iastate.models.Member.UserType;
import edu.iastate.utils.MemberUtils;

@Controller
@RequestMapping(value = "/context")
public class ContextController {

    @RequestMapping(method = RequestMethod.POST)
    public String updateContext(
            @RequestParam(value = "context", required = true) int context,
            Model model,
            HttpSession session) {

        Member member = (Member) session.getAttribute("member");
        MemberDao memberDao = new MemberDao();
        member = memberDao.getMemberById(member.getId());
        if (member == null) {
            return "redirect:/denied";
        }

        String redirect;

        if (context == UserType.PLAYER.ordinal()) {
            member.setContext(UserType.PLAYER);
            redirect = "redirect:/profile";

        } else if (context == UserType.OFFICIAL.ordinal() &&
                MemberUtils.atLeastOfficial(member)) {
            member.setContext(UserType.OFFICIAL);
            redirect = "redirect:/official";

        } else if (context == UserType.COORDINATOR.ordinal() &&
                MemberUtils.atLeastCoordinator(member)) {
            member.setContext(UserType.COORDINATOR);
            redirect = "redirect:/coordinator";

        } else if (context == UserType.ADMIN.ordinal() &&
                member.getUserType() == UserType.ADMIN) {
            member.setContext(UserType.ADMIN);
            redirect = "redirect:/admin";

        } else {
            return "redirect:/denied";
        }

        memberDao.save(member);
        session.setAttribute("member", memberDao.getMemberById(member.getId()));

        return redirect;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String loadContextSpecificPage(Model model, HttpSession session) {
        Member member = (Member) session.getAttribute("member");
        if (member == null) {
            return "redirect:/denied";
        }

        UserType context = member.getContext();

        if (context == UserType.PLAYER) {
            return "redirect:/profile";

        } else if (context == UserType.OFFICIAL) {
            return "redirect:/official";

        } else if (context == UserType.COORDINATOR) {
            return "redirect:/coordinator";

        } else {
            return "redirect:/admin";
        }
    }
}
