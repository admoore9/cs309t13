package edu.iastate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import edu.iastate.models.Game;
import edu.iastate.utils.EntityManagerFactorySingleton;

/**
 * Data Access Object for the Game class, this should be used for
 * interacting with the Game table in the database.
 * 
 * @author Andrew
 *
 */
public class GameDao {

    private final EntityManagerFactory entityManagerFactory;

    /**
     * standard constructor
     */
    public GameDao() {
        this.entityManagerFactory = EntityManagerFactorySingleton.getFactory();
    }

    /**
     * Can use a custom EntityManagerFactory for unit testing
     * 
     * @param entityManagerFactory The factory to use to get sessions
     */
    public GameDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * Gets a list of all the games in the database
     * 
     * @return List of all the games
     */
    public List<Game> getAllGames() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Game> query = entityManager.createQuery("from Game", Game.class);
        List<Game> games = query.getResultList();

        transaction.commit();
        entityManager.close();
        return games;
    }

    /**
     * Gets a Game matching the given id
     * 
     * @param id The id of the game you wish to fetch
     * @param getTeams Whether the teams should be fetched
     * @return The game with the given id
     */
    public Game getGameById(int id, boolean getTeams) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Game> query = entityManager.createQuery("select g from Game g where g.id = :id", Game.class);
        query.setParameter("id", id);
        Game game = query.getSingleResult();
        loadForeignKeys(game, getTeams);

        transaction.commit();
        entityManager.close();
        return game;
    }

    /**
     * Saves the given game to the database
     * 
     * @param game the tame to save to the database
     */
    public void saveGame(Game game) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.merge(game);

        transaction.commit();
        entityManager.close();
    }
    
    /**
     * Loads the foreign keys for a game based on the booleans
     * 
     * @param game The game to load the foreign keys for
     * @param getTeams Whether to get the teams for the tournament
     */
    private void loadForeignKeys(Game game, boolean getTeams) {
    	if (getTeams)
    		loadTeams(game);
    }

    /**
     * Loads the teams for a game
     * 
     * @param game The game to load teams for
     */
    private void loadTeams(Game game) {
     game.getTeams().size();
    }
}
