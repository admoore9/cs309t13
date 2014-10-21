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
    public Player getPlayerById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Player> query = entityManager.createQuery("from Player p where p.member_id = " + id, Player.class);
        Player player = query.getSingleResult();

        transaction.commit();
        entityManager.close();
        return player;
    }

    public void register(String name, String username, String password) {
        Player player = new Player(name, username, password);
        
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.merge(player);

        transaction.commit();
        entityManager.close();
    }
    
    /**
     * Saves the given player to the database
     * 
     * @param player The player to save to the database
     */
    public void savePlayer(Player player) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.merge(player);

        transaction.commit();
        entityManager.close();
    }
}
