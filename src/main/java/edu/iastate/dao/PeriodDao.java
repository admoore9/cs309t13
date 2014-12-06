package edu.iastate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import edu.iastate.models.Period;

public class PeriodDao extends BaseDao<Period> {

    public void savePeriods(List<Period> periods) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        for (Period period : periods)
            entityManager.merge(period);

        transaction.commit();
        entityManager.close();
    }

    public void update(Period period) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Period periodToUpdate = entityManager.merge(period);
        periodToUpdate.setAvailable(period.isAvailable());
        transaction.commit();
        entityManager.close();
    }

}
