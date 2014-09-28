package edu.iastate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import edu.iastate.models.Tournament;
import edu.iastate.utils.DatabaseUtil;

public class TournamentDao {

    private static TournamentDao tournamentDao = new TournamentDao();

    private final EntityManagerFactory entityManagerFactory;

    /**
     * Prevent instantiation
     */
    private TournamentDao() {
        this.entityManagerFactory = DatabaseUtil.getEntityManagerFactory();
    }

    public static TournamentDao getInstance() {
        return tournamentDao;
    }
    
    public static void setInstance(TournamentDao tournamentDao) {
        TournamentDao.tournamentDao = tournamentDao;
    }

    /**
     * Gets a list of all the tournaments in the database
     * 
     * @return List of all the tournaments
     */
    public List<Tournament> getAllTournaments() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Tournament> query = entityManager.createQuery("from Tournament", Tournament.class);
        List<Tournament> tournaments = query.getResultList();

        transaction.commit();
        entityManager.close();
        return tournaments;
    }

    /**
     * Gets a tournament matching the given id
     * 
     * @param id The id of the tournament you wish to fetch
     * @return The tournament with the given id
     */
    public Tournament getTournamentById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Tournament> query = entityManager.createQuery("select t from Tournament t where t.id = :id",
                Tournament.class);
        query.setParameter("id", id);
        Tournament tournament = query.getSingleResult();

        transaction.commit();
        entityManager.close();
        return tournament;
    }
}
