package edu.iastate.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import edu.iastate.models.Availability;
import edu.iastate.utils.EntityManagerFactorySingleton;

public class AvailabilityDao {

    private final EntityManagerFactory entityManagerFactory;
    
    public AvailabilityDao() {
        this.entityManagerFactory = EntityManagerFactorySingleton.getFactory();
    }
    
    public AvailabilityDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
    
    /**
     * Saves the given availability to the database
     * 
     * @param member The member to save to the database
     */
    public Availability saveAvailability(Availability availability) {
        
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Availability savedAvailability = entityManager.merge(availability);
        
        transaction.commit();
        entityManager.close();

        return savedAvailability;
    }

    public Availability getAvailabilityById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Availability> query = entityManager.createQuery("from Availability a where a.availability_id = :id", Availability.class);
        query.setParameter("id", id);
        Availability availability = query.getSingleResult();

        transaction.commit();
        entityManager.close();
        return availability;
    }

}
