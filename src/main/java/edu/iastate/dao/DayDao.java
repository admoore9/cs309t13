package edu.iastate.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import edu.iastate.models.Day;
import edu.iastate.utils.EntityManagerFactorySingleton;

public class DayDao {

    private final EntityManagerFactory entityManagerFactory;

    public DayDao() {
        this.entityManagerFactory = EntityManagerFactorySingleton.getFactory();
    }
    
    public DayDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
    
    /**
     * Saves the given availability to the database
     *
     * @param member The member to save to the database
     */
    public void saveDay(Day day) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(day);

        entityManager.merge(day);

        transaction.commit();
        entityManager.close();
    }

    public List<Day> saveDays(List<Day> days) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        List<Day> savedDays = new ArrayList<Day>();
        for (Day day : days)
            savedDays.add(entityManager.merge(day));
            
        transaction.commit();
        entityManager.close();

        return savedDays;
    }

}
