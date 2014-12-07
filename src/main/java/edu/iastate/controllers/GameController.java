package edu.iastate.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
import edu.iastate.dao.MessageDao;
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

    /**
     * Returns the view for the game given by id
     * 
     * @param model The model for the view
     * @param session The http session
     * @param id The id of the game
     * @return The jsp page for the view
     */
    @RequestMapping(value = "/{id}/view", method = RequestMethod.GET)
    public String getGame(Model model, HttpSession session, @PathVariable int id) {

        Member member = (Member) session.getAttribute("member");
        MemberDao memberDao = new MemberDao();
        if (member == null) {
            return "redirect:/denied";
        }
        member = memberDao.getMemberById(member.getId());

        GameDao gameDao = new GameDao();
        Game game = gameDao.getGameById(id, true);
        if (game == null) {
            return "redirect:/denied";
        }

        model.addAttribute("gameId", id);
        model.addAttribute("game", game);

        // For sidebar
        Set<Team> teams = member.getTeams();
        model.addAttribute("teams", teams);

        // For sidebar
        TournamentDao tournamentDao = new TournamentDao();
        List<Tournament> tournaments = tournamentDao.getLastXTournaments(5);
        model.addAttribute("tournaments", tournaments);

        session.setAttribute("member", memberDao.getMemberById(member.getId()));
        return "game";
    }

    /**
     * Updates the game given the location, official to add or remove. Does
     * nothing for the fields that are left blank and sumbit is pressed
     * 
     * 
     * @param id the game ID
     * @param location the new location of the game
     * @return true if successful
     * @throws ParseException
     */
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public @ResponseBody boolean updateGame(
            @PathVariable int id,
            @RequestParam(value = "location") String location,
            @RequestParam(value = "gameTime") String gameTimeStr,
            @RequestParam(value = "addOfficial") String addOfficial,
            @RequestParam(value = "removeOfficial") String removeOfficial,
            HttpSession session) throws ParseException {

        // Validates the user permission
        Member member = (Member) session.getAttribute("member");
        if (!MemberUtils.atLeastCoordinator(member)) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date gameTime = sdf.parse(gameTimeStr);

        GameDao gameDao = new GameDao();
        MemberDao memberDao = new MemberDao();
        MessageDao messageDao = new MessageDao();
        Game game = gameDao.getGameById(id, true);
        // update location
        if (location != "") {
            game.setGameLocation(location);
            // notify teams of location change
            messageDao.notifyGameTeams(game, member.getName() + " has changed the location of " + game.getTournament().getName() + "'s game " + game.getId() + " to "
                    + location);
        }
        // update time
        game.setGameTime(gameTime);
        // notify teams of time change
        messageDao.notifyGameTeams(game, member.getName() + " has changed the time of " + game.getTournament().getName() + "'s game " + game.getId() + " to " + gameTime);
        // remove official
        Member official = memberDao.getMemberByUsername(removeOfficial);
        boolean removed = game.removeOfficial(official);
        if (removed) // notify member of being no longer official of the game
            messageDao.notify(official, member.getName() + " has removed you from being offical of " + game.getTournament().getName() + "'s game " + game.getId());
        // add official
        int added = game.addOfficial(memberDao.getMemberByUsername(addOfficial));
        if (added == 1) // notify game teams of the new official
            messageDao.notifyGameTeams(game, member.getName() + " has added " + official.getName() + " as offical of " + game.getTournament().getName() + "'s game "
                    + game.getId());
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
        if (session.getAttribute("member") == null) {
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

        // TODO check to make sure the game belongs to the official or
        // tournament to coordinator
        Member me = (Member) session.getAttribute("member");
        if (me == null || me.getUserType() == Member.UserType.PLAYER) {
            return false;
        }

        GameDao gameDao = new GameDao();
        Game game = gameDao.getGameById(id, true);

        TeamDao teamDao = new TeamDao();
        Team team = teamDao.getTeamById(winnerId, true, true, true);

        game.setWinner(team);
        gameDao.saveGame(game);

        new MessageDao().notifyGameTeams(game, team.getName() + " has won " + game.getTournament().getName() + "'s game " + game.getId());

        return true;
    }
}
