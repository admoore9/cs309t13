package edu.iastate.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    /**
     * returns the view for the index page
     * 
     * @param m the model for the view
     * @param session the http session
     * @return The jsp page for the view
     */
    @RequestMapping(method = RequestMethod.GET)
    public String loadProfilePage(Model m, HttpSession session) {

        if (session.getAttribute("member") == null) {
            return "redirect:/denied";
        }

        session.invalidate();
        return "redirect:/";
    }

}
