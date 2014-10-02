package edu.iastate.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.iastate.dao.GameDao;
import edu.iastate.models.Game;

@Controller
@RequestMapping("/game")
public class GameController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String getGame(Model model) {
		GameDao gameDao = new GameDao();
		List<Game> games = gameDao.getAllGames();
		model.addAttribute("games", games);
		return "game";
	}
	
}
