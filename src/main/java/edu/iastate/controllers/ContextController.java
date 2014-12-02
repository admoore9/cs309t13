package edu.iastate.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.iastate.models.Member.UserType;

@Controller
@RequestMapping(value = "/context")
public class ContextController {

    @RequestMapping(method = RequestMethod.POST)
    public boolean updateContext(
            Model model,
            HttpSession session,
            @RequestParam(value = "context") UserType context) {
        System.out.println(context);
        return true;
    }
}
