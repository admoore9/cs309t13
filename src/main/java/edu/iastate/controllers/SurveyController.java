package edu.iastate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.iastate.dao.PlayerDao;

@Controller
@RequestMapping("/survey")
public class SurveyController {

    // private PlayerDao playerDao = new PlayerDao();

    @RequestMapping(method = RequestMethod.GET)
    public String getSurvey(Model model) {
        // Player player = playerDao.getPlayerById(1);
        // System.out.println(player.getName());
        return "survey";
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody String surveySubmit(@ModelAttribute(value = "sex") String sex) {
        return sex;
    }
}
