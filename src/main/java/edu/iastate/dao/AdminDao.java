package edu.iastate.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;

import edu.iastate.models.Admin;
import edu.iastate.models.Member;
import edu.iastate.models.Member.UserType;

public class AdminDao extends CoordinatorDao {

    public AdminDao() {
        super();
    }

    public AdminDao(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    /**
     * Get a list of all admins in database
     * @return List of admins in database
     */
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
    public Admin getAdminById(int id) throws NoResultException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Admin> query = entityManager.createQuery("from Admin a where a.member_id = :id", Admin.class);
        query.setParameter("id", id);
        try {
        Admin admin = query.getSingleResult();

        transaction.commit();
        entityManager.close();
        return admin;
        } catch (NoResultException noResultException) {
            return null;
        }
    }

    public <T extends Member> void promote(final T member) {
        String memberClassName = member.getClass().getSimpleName().toUpperCase();
        String targetTable = member.getUserType().name();
        int id = member.getId();

        // return if the class of passed member is equal to its userType
        if (memberClassName.equals(targetTable)) {
            return;
        }
        // return if passed member has row in target table
        if (hasRowInTargetTable(targetTable, id) != null)
            return;

        // statements
        String playerSql = "insert into Player (member_id) values (" + id + ")";
        String officialSql = "insert into Official (member_id) values (" + id + ")";
        String coordinatorSql = "insert into Coordinator (member_id) values (" + id + ")";
        String adminSql = "insert into Admin (member_id, current_view) values (" + id + "," + UserType.ADMIN.ordinal() + ")";

        // add rows to tables
        // Member -> Player | Official | Coordinator | Admin
        if (memberClassName.equals("MEMBER") && targetTable.equals("PLAYER"))
            addRowsToTables(Arrays.asList(playerSql));
        else if (memberClassName.equals("MEMBER") && targetTable.equals("OFFICIAL"))
            addRowsToTables(Arrays.asList(playerSql, officialSql));
        else if (memberClassName.equals("MEMBER") && targetTable.equals("COORDINATOR"))
            addRowsToTables(Arrays.asList(playerSql, officialSql, coordinatorSql));
        else if (memberClassName.equals("MEMBER") && targetTable.equals("ADMIN"))
            addRowsToTables(Arrays.asList(playerSql, officialSql, coordinatorSql, adminSql));
        // Player -> Official | Coordinator | Admin
        else if (memberClassName.equals("PLAYER") && targetTable.equals("OFFICIAL"))
            addRowsToTables(Arrays.asList(officialSql));
        else if (memberClassName.equals("PLAYER") && targetTable.equals("COORDINATOR"))
            addRowsToTables(Arrays.asList(officialSql, coordinatorSql));
        else if (memberClassName.equals("PLAYER") && targetTable.equals("ADMIN"))
            addRowsToTables(Arrays.asList(officialSql, coordinatorSql, adminSql));
        // Official -> Coordinator | Admin
        else if (memberClassName.equals("OFFICIAL") && targetTable.equals("COORDINATOR"))
            addRowsToTables(Arrays.asList(coordinatorSql));
        else if (memberClassName.equals("OFFICIAL") && targetTable.equals("ADMIN"))
            addRowsToTables(Arrays.asList(coordinatorSql, adminSql));
        // Coordinator -> Admin
        else if (memberClassName.equals("COORDINATOR") && targetTable.equals("ADMIN"))
            addRowsToTables(Arrays.asList(adminSql));
    }

    private Member hasRowInTargetTable(String targetTable, int id) {
        Member existingMember = null;
        if (targetTable.equals("PLAYER"))
            existingMember = this.getPlayerById(id, false);
        else if (targetTable.equals("OFFICIAL"))
            existingMember = this.getOfficialById(id);
        else if (targetTable.equals("COORDINATOR"))
            existingMember = this.getCoordinatorById(id);
        else if (targetTable.equals("ADMIN"))
            existingMember = this.getAdminById(id);
        return existingMember;
    }

    private void addRowsToTables(final List<String> sqls) {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        final org.hibernate.Session sess = (org.hibernate.Session) entityManager.getDelegate();
        
        sess.doWork(
                new Work() {
                    public void execute(Connection connection) throws SQLException 
                    {
                        for (String sql : sqls) {
                            Transaction transaction = sess.beginTransaction();
                            Statement statement = connection.createStatement();
                            statement.addBatch(sql);
                            statement.executeBatch();
                            transaction.commit();
                            statement.close();
                        }
                    }
                }
            );
        entityManager.close();
    }
}
