package edu.iastate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {
    
    @RequestMapping(value="/test", method=RequestMethod.GET)
    public String test(Model model) {
        model.addAttribute("name", "something put in in the controller");
        return "test";
    }
}