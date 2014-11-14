package edu.iastate.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.iastate.dao.GameDao;
import edu.iastate.dao.ScoreDao;
import edu.iastate.dao.TeamDao;
import edu.iastate.models.Game;
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
            @RequestParam(value = "gameId") int gameId,
            @RequestParam(value = "teamId") int teamId) {
        Game game = (new GameDao()).getGameById(gameId, false);
        Team team = (new TeamDao()).getTeamById(teamId, false, false, false);
        ScoreDao scoreDao = new ScoreDao();
        return scoreDao.getScoreByGameAndTeam(game, team);
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
        Game game = (new GameDao()).getGameById(gameId, false);
        Team team = (new TeamDao()).getTeamById(teamId, false, false, false);
        ScoreDao scoreDao = new ScoreDao();
        Score score = scoreDao.getScoreByGameAndTeam(game, team);
        score.setScore(teamScore);
        return true;
    }
}
