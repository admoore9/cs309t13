package edu.iastate.controllers;

import javax.servlet.http.HttpSession;

import edu.iastate.utils.StringUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.iastate.dao.MemberDao;
import edu.iastate.dao.PlayerDao;
import edu.iastate.models.Member;
import edu.iastate.models.Player;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @RequestMapping(method = RequestMethod.GET)
    public String loadSurveyPage(Model m, HttpSession session) {

        if (session.getAttribute("member") != null) {
            return "redirect:denied";
        }

        return "register";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public @ResponseBody void registerSubmit(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password) {

        // set up database access object
        PlayerDao playerDao = new PlayerDao();

        // Generate secure password
        String genPassword = StringUtils.secureString(password);

        // Create new member
        Player newPlayer = new Player(name, username, genPassword);

        // Save new member to database
        playerDao.save(newPlayer);
    }

    @RequestMapping(value = "/available", method = RequestMethod.GET)
    public @ResponseBody String isUsernameAvailable(
            @RequestParam(value = "username") String username) {
        MemberDao memberDao = new MemberDao();
        Member member = memberDao.getMemberByUsername(username);
        String isValid;
        if (member == null) {
            isValid = "{ \"valid\": true }";
        }
        else {
            isValid = "{ \"valid\": false }";
        }
        return isValid;
    }

}
