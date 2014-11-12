package edu.iastate.controllers;

import javax.servlet.http.HttpSession;

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

    private static final double LBS_TO_KG_FACTOR = 0.453592;
    private static final double IN_TO_CM_FACTOR = 0.0254;

    @RequestMapping(method = RequestMethod.GET)
    public String loadSurveyPage(Model m, HttpSession session) {

        if (session.getAttribute("member") == null) {
            return "redirect:denied";
        }

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
            @RequestParam(value = "isClubPlayer") boolean isClubPlayer,
            HttpSession session) {

        // set up database access objects
        PlayerDao playerDao = new PlayerDao();
        SurveyDao surveyDao = new SurveyDao();
        TournamentDao tournamentDao = new TournamentDao();

        Player player = (Player) session.getAttribute("member");
        Survey survey = new Survey();
        // TODO use the correct tournament
        // get first tournament from database
        Tournament tournament = tournamentDao.getAllTournaments().get(0);

        if (sex != null)
            player.setSex(sex);
        if (height != null)
            player.setHeight(height);
        if (weight != null)
            player.setWeight(weight);

        int surveyScore = calcSurveyScore(sex, height, weight, compYears, intsPlayed, compLvl, isClubPlayer);

        // set the surveys parameters
        survey.setTournament(tournament);
        survey.setPlayer(player);
        survey.setSurveyScore(surveyScore);

        // Save updated player and survey to database
        playerDao.savePlayer(player);
        surveyDao.saveSurvey(survey);
    }

    /**
     * Using the results from the survey, this method determines
     * the survey score for the player
     * 
     * @param sex           Sex of the player
     * @param height        Height of the player
     * @param weight        Weight of the player
     * @param compYears     Competative years played in sport by player
     * @param intsPlayed    Intramurals tournaments in sport played by player
     * @param compLvl       Competitiveness of player
     * @param isClubPlayer  Wether or not the player has played in a club
     * @return score        The players survey score for the tournament out of 100
     */
    private int calcSurveyScore(String sex, Integer height, Integer weight, Integer compYears,
            Integer intsPlayed, Integer compLvl, boolean isClubPlayer) {

        double score, BMIScore;

        score = 0;
        BMIScore = calcBMIScore(height, weight);

        if (isClubPlayer)
            score += 5 * 0.1;
        score += BMIScore * 0.3;
        score += compYears * 0.3;
        score += intsPlayed * 0.1;
        score += compLvl * 0.2;

        score *= 20;
        return (int) score;
    }

    /**
     * Using a players height and weight, this method determines
     * a BMI score (out of 5) for the player.
     * 
     * @param height     Height of the player
     * @param weight     Weight of the player
     * @return BMIScore  BMIScore of the player out of 5
     */
    private double calcBMIScore(int height, int weight) {
        double weightKg, heightCm, heightCmSqr, BMI, BMIScore;
        BMIScore = 0;

        weightKg = weight * LBS_TO_KG_FACTOR;
        heightCm = height * IN_TO_CM_FACTOR;
        heightCmSqr = heightCm * heightCm;

        BMI = weightKg / heightCmSqr;

        // Underweight
        if (BMI < 18.5) {
            BMIScore = 3.5;
        }
        // Normal weight
        else if (BMI >= 18.5 && BMI < 24.9) {
            BMIScore = 5;
        }
        // Slightly overweight
        else if (BMI >= 24.9 && BMI < 27.4) {
            BMIScore = 4;
        }
        // Overweight
        else if (BMI >= 27.4 && BMI < 29.9) {
            BMIScore = 0.75;
        }
        // Obesity
        else if (BMI >= 29.9) {
            BMIScore = 0;
        }
        return BMIScore;
    }
}
