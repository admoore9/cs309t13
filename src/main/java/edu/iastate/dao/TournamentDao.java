package edu.iastate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import edu.iastate.models.Tournament;
import edu.iastate.utils.EntityManagerFactorySingleton;

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

    private void loadForeignKeys(Tournament tournament, boolean getGames, boolean getTeams) {
        if(getGames) {
            loadGames(tournament);
        }
        if(getTeams) {
            loadTeams(tournament);
        }
    }

    private void loadGames(Tournament tournament) {
        tournament.getGames().size();
    }

    private void loadTeams(Tournament tournament) {
        tournament.getTeams().size();
    }
}
