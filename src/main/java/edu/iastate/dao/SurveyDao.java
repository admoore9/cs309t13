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

        TypedQuery<Survey> query = entityManager.createQuery("select s from Survey s where s.id = :id", Survey.class);
        List<Survey> surveys = query.getResultList();

        transaction.commit();
        entityManager.close();
        return surveys;
    }
}
