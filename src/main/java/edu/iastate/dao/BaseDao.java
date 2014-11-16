package edu.iastate.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import edu.iastate.utils.EntityManagerFactorySingleton;

/**
 * Perfoms basic CRUD operations for a class. Assumes the name of the table is
 * the same as the class provided. Also assumes the id for the entity will be an
 * int named 'id' in the class.
 * 
 * @author brianshannan
 *
 * @param <E> The class to do CRUD operations for
 */
public abstract class BaseDao<E> {

    protected final EntityManagerFactory entityManagerFactory;
    private final String tableName;
    private final Class<E> clazz;

    public BaseDao() {
        this(EntityManagerFactorySingleton.getFactory());
    }

    @SuppressWarnings("unchecked")
    public BaseDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.clazz = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.tableName = this.clazz.getSimpleName();
    }

    /**
     * Gets an element by id.
     * 
     * @param id The id of the element you wish to fetch.
     * @return The element with the given id, or null if one doesn't exist.
     */
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

    /**
     * Performs a merge operation with the given element into the database and
     * returns the new element if further changes need to be made.
     * 
     * @param element The element to merge.
     * @return The new element, can use if further changes need to be made,
     *         otherwise ignore.
     */
    public E merge(E element) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        E newElement = entityManager.merge(element);

        transaction.commit();
        entityManager.close();

        return newElement;
    }

    /**
     * Performs a persist operation with the given element. Should only be used
     * to create a new entity in the database
     * 
     * @param element The element to persist.
     */
    public void persist(E element) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(element);

        transaction.commit();
        entityManager.close();
    }

    /**
     * Deletes the element associated with id if it exists.
     * 
     * @param id The id of the element you wish to delete.
     */
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
