package edu.iastate.controllers;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.iastate.dao.TeamDao;
import edu.iastate.dao.TournamentDao;
import edu.iastate.models.*;

@Controller
@RequestMapping("/team")
public class TeamController {

    @RequestMapping(method = RequestMethod.GET)
    public String getTeam(Model model, HttpSession session) {

        if (session.getAttribute("member") == null) {
            return "redirect:denied";
        }

        TeamDao teamdao = new TeamDao();
        Team team = teamdao.getTeamById(2, true, true);
        model.addAttribute("teams", team.getGames());
        return "team";
    }
}
