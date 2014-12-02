package edu.iastate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import edu.iastate.models.Member;
import edu.iastate.models.Tournament;
import edu.iastate.utils.EntityManagerFactorySingleton;

/**
 * Data Access Object for the Tournament class, this should be used for
 * interacting with the Tournament table in the database.
 * 
 * @author brianshannan
 *
 */
public class TournamentDao {

    private final EntityManagerFactory entityManagerFactory;

    /**
     * Standard constructor
     */
    public TournamentDao() {
        this.entityManagerFactory = EntityManagerFactorySingleton.getFactory();
    }

    /**
     * Can use a custom EntityManagerFactory for unit testing
     * 
     * @param entityManagerFactory The factory to use to get sessions
     */
    public TournamentDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
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
     * Get the last X tournaments from database
     * @param number Number of tournaments
     * @return List of last X tournaments
     */
    public List<Tournament> getLastXTournaments(int number) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Tournament> query = entityManager.createQuery("select t from Tournament t order by t.id",
                Tournament.class);
        query.setMaxResults(number);
        List<Tournament> tournaments = query.getResultList();

        transaction.commit();
        entityManager.close();
        return tournaments;
    }

    /**
     * Gets a tournament matching the given id
     * 
     * @param id The id of the tournament you wish to fetch
     * @param getGames Whether the games should be fetched
     * @param getTeams Whether the teams should be fetched
     * @return The tournament with the given id
     */
    public Tournament getTournamentById(int id, boolean getGames, boolean getTeams) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Tournament> query = entityManager.createQuery("select t from Tournament t where t.id = :id",
                Tournament.class);
        query.setParameter("id", id);
        Tournament tournament = query.getSingleResult();
        loadForeignKeys(tournament, getGames, getTeams);

        transaction.commit();
        entityManager.close();
        return tournament;
    }

    /**
     * Saves the given tournament to the database
     * 
     * @param tournament The tournament to save to the database
     */
    public void saveTournament(Tournament tournament) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.merge(tournament);

        transaction.commit();
        entityManager.close();
    }

    /**
     * Loads the foreign keys for a tournament based on the booleans
     * 
     * @param tournament The tournament to load the foreign keys for
     * @param getGames Whether to get the games for the tournament
     * @param getTeams Whether to get the teams for the tournament
     */
    private void loadForeignKeys(Tournament tournament, boolean getGames, boolean getTeams) {
        if(getGames) {
            loadGames(tournament);
        }
        if(getTeams) {
            loadTeams(tournament);
        }
    }

    /**
     * Loads the games for a tournament
     * 
     * @param tournament The tournament to load games for
     */
    private void loadGames(Tournament tournament) {
        tournament.getGames().size();
    }

    /**
     * Loads the teams for a tournament
     * 
     * @param tournament The tournament to load teams for
     */
    private void loadTeams(Tournament tournament) {
        tournament.getTeams().size();
    }
}
