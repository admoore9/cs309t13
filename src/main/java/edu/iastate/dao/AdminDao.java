package edu.iastate.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.jdbc.Work;

import edu.iastate.models.Admin;
import edu.iastate.models.Member;

public class AdminDao extends MemberDao {

    public AdminDao() {
        super();
    }

    public AdminDao(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    public List<Admin> getAllAdmins() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Admin> query = entityManager.createQuery("from Admin", Admin.class);
        List<Admin> admins = query.getResultList();

        transaction.commit();
        entityManager.close();

        return admins;
    }

    /**
     * Gets an admin matching the given id
     * 
     * @param id The id of the admin you wish to fetch
     * @return admin by id
     */
    public Admin getAdminById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Admin> query = entityManager.createQuery("from Admin a where a.member_id = :id", Admin.class);
        query.setParameter("id", id);
        Admin admin = query.getSingleResult();

        transaction.commit();
        entityManager.close();
        return admin;
    }

    public <T extends Member> void promote(final T member) {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        org.hibernate.Session sess = (org.hibernate.Session) entityManager.getDelegate();
        sess.doWork(
                new Work() {
                    public void execute(Connection connection) throws SQLException 
                    { 
                        String sql = "insert into Player (member_id) values (?)";
                        PreparedStatement statement = connection.prepareStatement(sql);
                         
                        statement.setString(1, member.getId() + "");
                        
                        statement.addBatch();
                        
                        statement.executeBatch();
                        statement.close();
                        connection.close();
                        entityManager.close();
                    }
                }
            );
        
    }
}
