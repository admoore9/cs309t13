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
import edu.iastate.utils.StringUtils;

@Controller
@RequestMapping("/register")
public class RegisterController {

    /**
     * Returns the page for registering.
     * 
     * @param m The model for the jsp page.
     * @param session The http session for the current user.
     * @return The registration page.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String loadSurveyPage(Model m, HttpSession session) {

        if (session.getAttribute("member") != null) {
            return "redirect:/denied";
        }

        return "register";
    }

    /**
     * Registers a user.
     * 
     * @param name The users name.
     * @param username The username the user wants.
     * @param password The password the user chose.
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public @ResponseBody void registerSubmit(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password) {

        // set up database access object
        MemberDao memberDao = new MemberDao();

        // Generate secure password
        String genPassword = StringUtils.secureString(password);

        // Create new member
        Member newMember = new Member(name, username, genPassword);

        // Save new member to database
        memberDao.save(newMember);
    }

    /**
     * Returns whether the given username is available.
     * 
     * @param username The desired username.
     * @return true if it is available, false otherwise.
     */
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
