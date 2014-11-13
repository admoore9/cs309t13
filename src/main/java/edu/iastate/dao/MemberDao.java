package edu.iastate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import edu.iastate.models.Member;
import edu.iastate.utils.EntityManagerFactorySingleton;

public class MemberDao {

    protected final EntityManagerFactory entityManagerFactory;

    /**
     * Standard constructor
     */
    public MemberDao() {
        this.entityManagerFactory = EntityManagerFactorySingleton.getFactory();
    }

    /**
     * Can use a custom EntityManagerFactory for unit testing
     * 
     * @param entityManagerFactory The factory to use to get sessions
     */
    public MemberDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * Login with given username and password 
     * @param username
     * @param password
     * @return Member matching given username and password
     */
    public Member login(String username, String password) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Member> query = entityManager.createQuery("select m from Member m where m.username=:username and m.password=:password", Member.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        Member member = query.getSingleResult();

        transaction.commit();
        entityManager.close();

        return member;
    }

    /**
     * Get all members in the database
     * @return List of members in database
     */
    public List<Member> getAllMembers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Member> query = entityManager.createQuery("from Member", Member.class);
        List<Member> members = query.getResultList();

        transaction.commit();
        entityManager.close();

        return members;
    }

    /**
     * Get member matching given id
     * @param id ID of member to search for
     * @return Member Member matching id
     */
    public Member getMemberById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Member> query = entityManager.createQuery("from Member m where m.member_id = :id", Member.class);
        query.setParameter("id", id);
        Member member = query.getSingleResult();

        transaction.commit();
        entityManager.close();
        return member;
    }

    /**
     * Saves the given member to the database
     * 
     * @param member The member to save to the database
     */
    public Member save(Member member) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Member memberToReturn = entityManager.merge(member);
        transaction.commit();
        entityManager.close();
        return memberToReturn;
    }
}
