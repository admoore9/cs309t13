package edu.iastate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.iastate.dao.PlayerDao;
import edu.iastate.dao.SurveyDao;
import edu.iastate.dao.TournamentDao;
import edu.iastate.models.Player;
import edu.iastate.models.Survey;
import edu.iastate.models.Tournament;

@Controller
@RequestMapping("/survey")
public class SurveyController {

    @RequestMapping(method = RequestMethod.GET)
    public String loadSurveyPage(Model m) {
        return "survey";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public @ResponseBody void surveySubmit(
            @RequestParam(value = "sex") String sex,
            @RequestParam(value = "height") Integer height,
            @RequestParam(value = "weight") Integer weight,
            @RequestParam(value = "compYears") Integer compYears,
            @RequestParam(value = "intsPlayed") Integer intsPlayed,
            @RequestParam(value = "compLvl") Integer compLvl,
            @RequestParam(value = "isClubPlayer") boolean isClubPlayer) {

        PlayerDao playerDao = new PlayerDao();
        SurveyDao surveyDao = new SurveyDao();
        TournamentDao tournamentDao = new TournamentDao();

        Player player = playerDao.getAllPlayers().get(0);
        Survey survey = new Survey();
        Tournament tournament = tournamentDao.getAllTournaments().get(0);

        if (sex != null)
            player.setSex(sex);
        if (height != null)
            player.setHeight(height);
        if (weight != null)
            player.setWeight(weight);

        survey.setTournament(tournament);
        survey.setPlayer(player);
        survey.setSurveyScore(calculateSurveyScore(sex, height, weight, compYears, intsPlayed, compLvl, isClubPlayer));

        playerDao.savePlayer(player);
        surveyDao.saveSurvey(survey);
    }

    private int calculateSurveyScore(String sex, Integer height, Integer weight,
            Integer compYears, Integer intsPlayed, Integer compLvl, boolean isClubPlayer) {
        int score = 5;
        return score;
    }
}
