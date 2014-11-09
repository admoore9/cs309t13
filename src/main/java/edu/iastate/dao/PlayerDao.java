package edu.iastate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import edu.iastate.models.Player;

public class PlayerDao extends MemberDao {

    public PlayerDao() {
        super();
    }

    public PlayerDao(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    /**
     * @return List of all players
     */
    public List<Player> getAllPlayers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Player> query = entityManager.createQuery("from Player", Player.class);
        List<Player> players = query.getResultList();

        transaction.commit();
        entityManager.close();

        return players;
    }

    /**
     * Gets a player matching the given id
     * 
     * @param id The id of the player you wish to fetch
     * @return player by id
     */
    public Player getPlayerById(int id, boolean getSurveys) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Player> query = entityManager.createQuery("from Player p where p.member_id = :id", Player.class);
        query.setParameter("id", id);
        Player player = query.getSingleResult();
        loadForeignKeys(player, getSurveys);

        transaction.commit();
        entityManager.close();
        return player;
    }

    /**
     * Loads the foreign keys for a player based on the booleans
     * 
     * @param player the player to load the foreign keys for
     * @param getSurveys Whether to get the survey list for player
     */
    private void loadForeignKeys(Player player, boolean getSurveys) {
        if(getSurveys) {
            loadSurveys(player);
        }
    }

    /**
     * Loads the Surveys for a player
     * 
     * @param player the player to load surveys for
     */
    private void loadSurveys(Player player) {
        player.getSurveys().size();
    }
}
