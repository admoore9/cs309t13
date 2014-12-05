package edu.iastate.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.iastate.dao.MemberDao;
import edu.iastate.models.Member;
import edu.iastate.models.Member.UserType;
import edu.iastate.utils.MemberUtils;

@Controller
@RequestMapping(value = "/context")
public class ContextController {

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody String updateContext(
            @RequestParam(value = "context", required = true) int context,
            Model model,
            HttpSession session) {

        Member member = (Member) session.getAttribute("member");

        if (member == null)
            return "redirect:/denied";

        if (context == member.getContext().ordinal()) {
            return null;

        } else if (context == UserType.PLAYER.ordinal()) {
            member.setContext(UserType.PLAYER);

        } else if (context == UserType.OFFICIAL.ordinal() &&
                MemberUtils.atLeastOfficial(member)) {
            member.setContext(UserType.OFFICIAL);

        } else if (context == UserType.COORDINATOR.ordinal() &&
                MemberUtils.atLeastCoordinator(member)) {
            member.setContext(UserType.COORDINATOR);

        } else if (context == UserType.ADMIN.ordinal() &&
                member.getUserType() == UserType.ADMIN) {
            member.setContext(UserType.ADMIN);

        } else {
            return "redirect:/denied";
        }

        MemberDao memberDao = new MemberDao();
        memberDao.save(member);
        session.setAttribute("member", memberDao.getMemberById(member.getId()));
        return null;
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
