package edu.iastate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import edu.iastate.models.Member;
import edu.iastate.models.Tournament;
import edu.iastate.utils.EntityManagerFactorySingleton;

public class MemberDao {
	
    private final EntityManagerFactory entityManagerFactory;

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
        // Didn't use query.getFirstResult() because it throws a NoResultException
        Member member = query.getResultList().get(0);

        transaction.commit();
        entityManager.close();
        
    	return member;
    }
    

    public void register(String name, String username, String password, int isAdmin, int isOfficial) {
    	// INSERT INTO `cs309t13`.`Member` (`member_id`, `member_name`, `is_admin`, `is_official`) VALUES ('1', 'nawaf', '1', '0');
    	
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Member> query = entityManager.createQuery("INSERT INTO `Member` (`member_name`, `member_username`, `member_password`, `is_admin`, `is_official`) VALUES (':name', ':username', ':password', ':isAdmin', ':isOfficial')", Member.class);
        query.setParameter("name", name);
        query.setParameter("username", username);
        query.setParameter("password", password);
        query.setParameter("isAdmin", isAdmin);
        query.setParameter("isOfficial", isOfficial);

        transaction.commit();
        entityManager.close();

    }
    

    public void addAdminType(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        
        // SELECT * FROM cs309t13.Member where member_id=1;
        TypedQuery<Member> query = entityManager.createQuery("UPDATE Member SET is_admin='1' WHERE member_id=:id", Member.class); //select m from Member m where m.member_id=:id
        query.setParameter("id", id);

        transaction.commit();
        entityManager.close();
    }
    
    public void removeAdminType(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        
        // SELECT * FROM cs309t13.Member where member_id=1;
        TypedQuery<Member> query = entityManager.createQuery("UPDATE Member SET is_admin='0' WHERE member_id=:id", Member.class); //select m from Member m where m.member_id=:id
        query.setParameter("id", id);

        transaction.commit();
        entityManager.close();
    }
    
    public void addOfficialType(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        
        // SELECT * FROM cs309t13.Member where member_id=1;
        TypedQuery<Member> query = entityManager.createQuery("UPDATE Member SET is_official='1' WHERE member_id=:id", Member.class); //select m from Member m where m.member_id=:id
        query.setParameter("id", id);

        transaction.commit();
        entityManager.close();
    }
    
    public void removeOfficialType(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        
        // SELECT * FROM cs309t13.Member where member_id=1;
        TypedQuery<Member> query = entityManager.createQuery("UPDATE Member SET is_official='0' WHERE member_id=:id", Member.class); //select m from Member m where m.member_id=:id
        query.setParameter("id", id);

        transaction.commit();
        entityManager.close();
    }
    
    public List<Member> returnMembers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Member> query = entityManager.createQuery("from Member", Member.class);
        List<Member> members = query.getResultList();

        transaction.commit();
        entityManager.close();
        
    	return members;
    }
}
