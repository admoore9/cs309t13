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
import edu.iastate.dao.TeamDao;
import edu.iastate.models.Game;
import edu.iastate.models.Member;
import edu.iastate.models.Team;

@Controller
@RequestMapping("/game")
public class GameController {

    @RequestMapping(method = RequestMethod.GET)
    public String getGame(Model model, HttpSession session) {

        if (session.getAttribute("member") == null) {
            return "redirect:denied";
        }

        GameDao gameDao = new GameDao();
        List<Game> games = gameDao.getAllGames();
        model.addAttribute("games", games);
        return "game";
    }

    /**
     * Gets the game given by id as JSON.
     * 
     * @param id The id of the game.
     * @return JSON representation of the game.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Game getGame(HttpSession session, @PathVariable int id) {
        if(session.getAttribute("member") == null) {
            return null;
        }

        GameDao gameDao = new GameDao();
        return gameDao.getGameById(id, true);
    }

    /**
     * Sets the winner of a game.
     * 
     * @param session The http session for the user.
     * @param id The id of the game to set the winner of.
     * @param winnerId The id of the team that should be set as the winner.
     * @return true if the game winner was set successfully, false otherwise.
     */
    @RequestMapping(value = "/{id}/winner", method = RequestMethod.POST)
    public @ResponseBody boolean setGameWinner(
            HttpSession session,
            @PathVariable int id,
            @RequestParam(value = "winner") int winnerId) {
        Member me = (Member) session.getAttribute("member");
        if(me == null || me.getUserType() == Member.UserType.PLAYER) {
            return false;
        }

        GameDao gameDao = new GameDao();
        Game game = gameDao.getGameById(id, true);

        TeamDao teamDao = new TeamDao();
        Team team = teamDao.getTeamById(winnerId, true, true, true);

        game.setWinner(team);
        gameDao.saveGame(game);

        return true;
    }
}
