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
import edu.iastate.dao.MemberDao;
import edu.iastate.dao.TeamDao;
import edu.iastate.dao.TournamentDao;
import edu.iastate.models.Game;
import edu.iastate.models.Member;
import edu.iastate.models.Team;
import edu.iastate.models.Tournament;
import edu.iastate.utils.MemberUtils;


@Controller
@RequestMapping("/game")
public class GameController {

    @RequestMapping(value = "/{id}/view", method = RequestMethod.GET)
    public String getGame(Model model, HttpSession session, @PathVariable int id) {
        model.addAttribute("gameId", id);
        if (session.getAttribute("member") == null) {
            return "redirect:../../denied";
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
     * Updates the game given the location, official to add or remove
     * Does nothing for the fields that are left blank and sumbit is pressed
     * 
     * 
     * @param id the game ID
     * @param location the new location of the game
     * @return true if successful
     */
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public @ResponseBody boolean updateGame(
            @PathVariable int id,
            @RequestParam(value = "location") String location,
            @RequestParam(value = "addOfficial") String addOfficial,
            @RequestParam(value = "removeOfficial") String removeOfficial,
            HttpSession session) {
        //Validates the user permission
        Member member = (Member) session.getAttribute("member");
        if(MemberUtils.atLeastCoordinator(member)) {
            return false;
        }

        GameDao gameDao = new GameDao();
        MemberDao memberDao = new MemberDao();
        Game game = gameDao.getGameById(id, true);
        if(location!="") {
            game.setGameLocation(location);
        }
        game.removeOfficial(memberDao.getMemberByUsername(removeOfficial));
        game.addOfficial(memberDao.getMemberByUsername(addOfficial));

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
