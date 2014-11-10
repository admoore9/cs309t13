package edu.iastate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.iastate.dao.PlayerDao;
import edu.iastate.models.Player;

@Controller
@RequestMapping(value = "/player")
public class PlayerController {

    @RequestMapping(value = "/{id}/view", method = RequestMethod.GET)
    public String playerView(Model model, @PathVariable int id) {
        PlayerDao playerDao = new PlayerDao();
        Player player = playerDao.getPlayerById(id, false);
        model.addAttribute("player", player);
        model.addAttribute("teams", player.getTeams());
        return "player";
    }
}
