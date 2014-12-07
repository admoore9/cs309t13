package edu.iastate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/denied")
public class DeniedController {

    /**
     * Returns the view for the denied page
     * 
     * @return The jsp page for the view
     */
    @RequestMapping(method = RequestMethod.GET)
    public String loadProfilePage() {
        return "denied";
    }

}
