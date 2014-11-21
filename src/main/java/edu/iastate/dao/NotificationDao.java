package edu.iastate.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import edu.iastate.models.Notification;
import edu.iastate.utils.EntityManagerFactorySingleton;

public class NotificationDao {

    protected final EntityManagerFactory entityManagerFactory;

    /**
     * Standard constructor
     */
    public NotificationDao() {
        this.entityManagerFactory = EntityManagerFactorySingleton.getFactory();
    }

    /**
     * Can use a custom EntityManagerFactory for unit testing
     * 
     * @param entityManagerFactory The factory to use to get sessions
     */
    public NotificationDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public Notification save(Notification Notification) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Notification savedNotification = entityManager.merge(Notification);
        transaction.commit();
        entityManager.close();
        return savedNotification;
    }
}
