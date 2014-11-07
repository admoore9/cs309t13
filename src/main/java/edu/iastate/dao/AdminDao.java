package edu.iastate.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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

    public <T extends Member> void promote(T member) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("INSERT INTO Player (:id) SELECT :id from Member");
//        TypedQuery<Member> query = entityManager.createQuery("UPDATE Member SET member_name=:name WHERE member_id=:id", Member.class);
        query.setParameter("id", member.getId());

        transaction.commit();
        entityManager.close();
        
        
//        Connection connection = new getConnection();
//        Statement statement = connection.createStatement();
//         
//        for (Employee employee: employees) {
//            String query = "insert into employee (name, city) values('"
//                    + employee.getName() + "','" + employee.getCity + "')";
//            statement.addBatch(query);
//        }
//        statement.executeBatch();
//        statement.close();
//        connection.close();
    }
}
