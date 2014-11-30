package edu.iastate.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.iastate.dao.GameDao;
import edu.iastate.dao.TournamentDao;
import edu.iastate.models.Game;
import edu.iastate.models.Member;
import edu.iastate.models.Team;
import edu.iastate.models.Tournament;

@Controller
@RequestMapping("/game")
public class GameController {

    @RequestMapping(value = "/{id}/view", method = RequestMethod.GET)
    public String getGame(Model model, HttpSession session, @PathVariable int id) {
        model.addAttribute("gameId", id);
        if (session.getAttribute("member") == null) {
            return "redirect:denied";
        }
        
        Member member = (Member) session.getAttribute("member");

        List<Team> teams = member.getTeams();
        model.addAttribute("teams", teams);

        TournamentDao tournamentDao = new TournamentDao();
        List<Tournament> tournaments = tournamentDao.getLastXTournaments(5);
        model.addAttribute("tournaments", tournaments);

        GameDao gameDao = new GameDao();
        Game game = gameDao.getGameById(id, true);
        model.addAttribute("game", game);
        return "game";
    }
    
    /**
     * Updates the game given the location 
     * 
     * @param id the game ID
     * @param location the new location of the game
     * @return true if successful
     */
    // TODO check users permissions
    // TODO check if valid tournament
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public @ResponseBody boolean updateGame(
            @PathVariable int id,
            @RequestParam(value = "location") String location) {
        GameDao gameDao = new GameDao();
        Game game = gameDao.getGameById(id, true);
        System.out.println("Updating ... ");
        game.setGameLocation(location);
        
        gameDao.saveGame(game);
        return true;
    }

    /**
     * Gets the game given by id as JSON.
     * 
     * @param id The id of the game.
     * @return JSON representation of the game.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Game getGame(@PathVariable int id) {
        GameDao gameDao = new GameDao();
        return gameDao.getGameById(id, true);
    }
}
