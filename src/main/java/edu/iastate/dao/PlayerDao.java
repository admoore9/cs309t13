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

    public List<Player> returnAllPlayers() {
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
