package edu.iastate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import edu.iastate.models.Coordinator;

public class CoordinatorDao extends OfficialDao {

    public CoordinatorDao() {
        super();
    }

    public CoordinatorDao(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    /**
     * @return List of all coordinators
     */
    public List<Coordinator> getAllCoordinators() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Coordinator> query = entityManager.createQuery("from Coordinator", Coordinator.class);
        List<Coordinator> coordinators = query.getResultList();

        transaction.commit();
        entityManager.close();

        return coordinators;
    }

    /**
     * Gets a coordinator matching the given id
     * 
     * @param id The id of the coordinator you wish to fetch
     * @return coordinator by id
     */
    public Coordinator getCoordinatorById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Coordinator> query = entityManager.createQuery("from coordinator p where p.member_id = :id", Coordinator.class);
        query.setParameter("id", id);
        Coordinator coordinator = query.getSingleResult();

        transaction.commit();
        entityManager.close();
        return coordinator;
    }
}
