package edu.iastate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import edu.iastate.models.Survey;
import edu.iastate.utils.EntityManagerFactorySingleton;

/**
 * Data Access Object for the Survey class, this should be used for interacting
 * with the Survey table in the database.
 * 
 * @author Andrew
 *
 */
public class SurveyDao {

    private final EntityManagerFactory entityManagerFactory;

    /**
     * standard constructor
     */
    public SurveyDao() {
        this.entityManagerFactory = EntityManagerFactorySingleton.getFactory();
    }

    /**
     * Can use a custom EntityManagerFactory for unit testing
     * 
     * @param entityManagerFactory The factory to use to get sessions
     */
    public SurveyDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * Gets a list of all the surveys in the database
     * 
     * @return List of all the surveys
     */
    public List<Survey> getAllSurveys() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Survey> query = entityManager.createQuery("from Survey", Survey.class);
        List<Survey> surveys = query.getResultList();

        transaction.commit();
        entityManager.close();
        return surveys;
    }
    
    /**
     * Gets the survey for a player in a particular survey
     * 
     * @param player_id The player id to get the survey for
     * @param tournament_id The tournament id to get the survey for
     * @return The survey corresponding to the player and tournament id
     */
    public Survey getSurvey(int tournament_id, int player_id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Survey> query = entityManager.createQuery("select s from Survey s where s.tournament_id = :tournament_id"
                + "and s.player_id = :player_id", Survey.class);
        query.setParameter("tournament_id", tournament_id);
        query.setParameter("player_id", player_id);
        Survey survey = query.getSingleResult();

        transaction.commit();
        entityManager.close();
        return survey;
    }

    /**
     * Saves the given survey to the database
     * 
     * @param survey The Survey to save to the database
     */
    public void saveSurvey(Survey survey) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.merge(survey);

        transaction.commit();
        entityManager.close();
    }
}