package edu.iastate.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.iastate.dao.GameDao;
import edu.iastate.dao.MessageDao;
import edu.iastate.dao.ScoreDao;
import edu.iastate.dao.TeamDao;
import edu.iastate.models.Game;
import edu.iastate.models.Member;
import edu.iastate.models.Score;
import edu.iastate.models.Team;

@Controller
@RequestMapping(value = "/score")
public class ScoreController {

    /**
     * Returns the score associated with a given team and game.
     * 
     * @param gameId The id of the game.
     * @param teamId The id of the team.
     * @return The score associated with the game and team given by gameId and
     *         teamId.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody Score getScore(
            HttpSession session,
            @RequestParam(value = "gameId") int gameId,
            @RequestParam(value = "teamId") int teamId) {
        if(session.getAttribute("member") == null) {
            return null;
        }

        ScoreDao scoreDao = new ScoreDao();
        return scoreDao.getScoreByGameAndTeam(gameId, teamId);
    }
    
    /**
     * Updates the score associated with a given team and game.
     * 
     * @param teamScore The new score for the team.
     * @param gameId The id of the game.
     * @param teamId The id of the team.
     * @return true if it succeeded, false otherwise
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody boolean updateScore(
            HttpSession session,
            @RequestParam(value = "score") int teamScore,
            @RequestParam(value = "gameId") int gameId,
            @RequestParam(value = "teamId") int teamId) {
        Member me = (Member) session.getAttribute("member");
        // Don't allow players to update scores, everyone else should be able to
        if(me.getUserType() == Member.UserType.PLAYER) {
            return false;
        }

        ScoreDao scoreDao = new ScoreDao();
        Score score = scoreDao.getScoreByGameAndTeam(gameId, teamId);
        score.setScore(teamScore);
        // notify team of the new game score
        Team team = new TeamDao().getTeamById(teamId, false, true, false);
        Game game = new GameDao().getGameById(gameId, false);
        new MessageDao().notifyTeamPlayers(team, "Your new team score in " + game.getTournament().getName() + "'s game " + game.getId() + " is " + score.getScore());
        scoreDao.merge(score);
        return true;
    }
}
