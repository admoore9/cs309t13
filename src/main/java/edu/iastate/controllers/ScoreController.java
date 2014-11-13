package edu.iastate.controllers;

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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody Score getScoreByGameAndTeam(
            @RequestParam(value = "gameId") int gameId,
            @RequestParam(value = "teamId") int teamId) {
        ScoreDao scoreDao = new ScoreDao();
        GameDao gameDao = new GameDao();
        Game game = gameDao.getGameById(gameId, false);
        TeamDao teamDao = new TeamDao();
        Team team = teamDao.getTeamById(teamId, false, false, false);
        return scoreDao.getScoreByGameAndTeam(game, team);
    }
}
