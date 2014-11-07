package edu.iastate.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.iastate.dao.MemberDao;
import edu.iastate.models.Member;
import edu.iastate.utils.StringUtils;

@Controller
@RequestMapping("/login")
@SessionAttributes({ "member" })
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    public String loadLoginPage(ModelMap modelMap, HttpSession session) {

        if (session.getAttribute("member") != null) {
            return "redirect:denied";
        }

        return "login";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public @ResponseBody String registerSubmit(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            HttpSession session,
            Model model) {

        String msg;

        MemberDao memberDao = new MemberDao();

        String genPassword = StringUtils.secureString(password);

        Member member = memberDao.getMemberByUsernamePassword(username, genPassword);

        if (member != null) {
            session.setAttribute("member", member);
            model.addAttribute("member", member);
            msg = "redirect:profile";
        }
        else {
            msg = "redirect:login";
        }
        return msg;
    }
}
