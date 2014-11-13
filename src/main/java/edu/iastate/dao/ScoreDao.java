package edu.iastate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import edu.iastate.models.Game;
import edu.iastate.models.Score;
import edu.iastate.models.Team;

public class ScoreDao extends BaseDao<Score> {

    public Score getScoreByGameAndTeam(Game game, Team team) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        String queryString = "select s from Score s where s.game = :game and s.team = :team";
        TypedQuery<Score> query = entityManager.createQuery(queryString, Score.class);
        query.setParameter("game", game);
        query.setParameter("team", team);

        List<Score> scores = query.getResultList();
        Score score = null;
        if(scores.size() > 0) {
            score = scores.get(0);
        }

        transaction.commit();
        entityManager.close();

        return score;
    }
}
