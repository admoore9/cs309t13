package edu.iastate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import edu.iastate.models.Member;
import edu.iastate.models.Member.UserType;
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
     * Login with provided 
     * @param username
     * @param password
     * @return
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
     * register a new user
     * @param name Real name of user
     * @param username
     * @param password
     * @param userType One of the user types allowed in the system.
     */
    public void register(String name, String username, String password, UserType userType) {
        
        Member member = new Member(name, username, password, userType);
        
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.merge(member);

        transaction.commit();
        entityManager.close();
    }
    
    public void setName(int id, String newName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        
        TypedQuery<Member> query = entityManager.createQuery("UPDATE Member SET member_name=:name WHERE member_id=:id", Member.class);
        query.setParameter("name", newName);

        transaction.commit();
        entityManager.close();
    }
    
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
    
    public Member getMemberById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Member> query = entityManager.createQuery("from Member m where m.member_id = " + id, Member.class);
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
    public void saveMember(Member member) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.merge(member);

        transaction.commit();
        entityManager.close();
    }
}
