package edu.iastate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/denied")
public class DeniedController {

    @RequestMapping(method = RequestMethod.GET)
    public String loadProfilePage(Model m) {
        return "denied";
    }

}