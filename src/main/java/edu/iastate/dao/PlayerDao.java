package edu.iastate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import edu.iastate.models.Member;
import edu.iastate.models.Player;
import edu.iastate.utils.EntityManagerFactorySingleton;

public class PlayerDao extends MemberDao {
	
	private final EntityManagerFactory entityManagerFactory;
	
	public PlayerDao() {
        this.entityManagerFactory = EntityManagerFactorySingleton.getFactory();
    }

    /**
     * Can use a custom EntityManagerFactory for unit testing
     * 
     * @param entityManagerFactory The factory to use to get sessions
     */
    public PlayerDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
	
	public List<Player> returnPlayers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Player> query = entityManager.createQuery("from Player", Player.class);
        List<Player> players = query.getResultList();

        transaction.commit();
        entityManager.close();
        
    	return players;
    }
}
