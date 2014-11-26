package edu.iastate.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import edu.iastate.models.Mail;
import edu.iastate.utils.EntityManagerFactorySingleton;

public class MailDao {
    protected final EntityManagerFactory entityManagerFactory;

    /**
     * Standard constructor
     */
    public MailDao() {
        this.entityManagerFactory = EntityManagerFactorySingleton.getFactory();
    }

    /**
     * Can use a custom EntityManagerFactory for unit testing
     * 
     * @param entityManagerFactory The factory to use to get sessions
     */
    public MailDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
    
    /**
     * Saves the given mail to the database
     * 
     * @param mail The mail to save to the database
     */
    public Mail save(Mail mail) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Mail savedMail = entityManager.merge(mail);
        transaction.commit();
        entityManager.close();
        return savedMail;
    }
}
