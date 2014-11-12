package edu.iastate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.springframework.core.GenericTypeResolver;

import edu.iastate.utils.EntityManagerFactorySingleton;

public abstract class BaseDao<E> {

    private final String tableName;
    private final EntityManagerFactory entityManagerFactory;
    private final Class<E> clazz = (Class<E>) GenericTypeResolver.resolveTypeArgument(this.getClass(), BaseDao.class);

    public BaseDao(String tableName) {
        this.tableName = tableName;
        this.entityManagerFactory = EntityManagerFactorySingleton.getFactory();
    }

    public BaseDao(String tableName, EntityManagerFactory entityManagerFactory) {
        this.tableName = tableName;
        this.entityManagerFactory = entityManagerFactory;
    }

    public E getById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        String queryString = String.format("select e from %s e where e.id = :id", this.tableName);
        TypedQuery<E> query = entityManager.createQuery(queryString, clazz);
        query.setParameter("id", id);

        List<E> elements = query.getResultList();
        E element = null;
        if(elements.size() > 0) {
            element = elements.get(0);
        }

        transaction.commit();
        entityManager.close();

        return element;
    }

    public E merge(E element) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        E newElement = entityManager.merge(element);

        transaction.commit();
        entityManager.close();

        return newElement;
    }

    public void create(E element) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(element);

        transaction.commit();
        entityManager.close();
    }

    public void deleteById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        String queryString = String.format("select e from %s e where e.id = :id", this.tableName);
        TypedQuery<E> query = entityManager.createQuery(queryString, this.clazz);
        query.setParameter("id", id);

        List<E> elements = query.getResultList();
        if(elements.size() == 0) {
            return;
        }

        E element = elements.get(0);
        entityManager.remove(element);

        transaction.commit();
        entityManager.close();
    }
}
