package edu.iastate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import edu.iastate.models.Game;
import edu.iastate.models.Score;
import edu.iastate.models.Team;

public class ScoreDao extends BaseDao<Score> {

    /**
     * Gets the score associated with a given game and score.
     * 
     * @param game The game associated with the score.
     * @param team The team associated with the score.
     * @return The score associated with team and game.
     */
    public Score getScoreByGameAndTeam(int gameId, int teamId) {
        EntityManager entityManager = super.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        String queryString = "from Score s where s.game.id = :gameId and s.team.id = :teamId";
        TypedQuery<Score> query = entityManager.createQuery(queryString, Score.class);
        query.setParameter("gameId", gameId);
        query.setParameter("teamId", teamId);

        List<Score> scores = query.getResultList();
        Score score = null;
        if(scores.size() > 0) {
            score = scores.get(0);
        }
        if(score == null) {
            Game game = (new GameDao()).getGameById(gameId, false);
            Team team = (new TeamDao()).getTeamById(teamId, false, false, false);

            // Create a new score if one doesn't exist
            score = new Score(0, game, team);
            score = super.merge(score);
        }

        transaction.commit();
        entityManager.close();

        return score;
    }
}
