package edu.iastate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import edu.iastate.models.Official;

public class OfficialDao extends MemberDao {

    public OfficialDao() {
        super();
    }

    public OfficialDao(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    public List<Official> getAllOfficials() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Official> query = entityManager.createQuery("from Official", Official.class);
        List<Official> officials = query.getResultList();

        transaction.commit();
        entityManager.close();

        return officials;
    }

    /**
     * Saves the given official to the database
     * 
     * @param official The official to save to the database
     */
    public void saveOfficial(Official official) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.merge(official);

        transaction.commit();
        entityManager.close();
    }
}
