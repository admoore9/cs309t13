package edu.iastate.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import edu.iastate.models.Member;
import edu.iastate.models.Message;
import edu.iastate.utils.EntityManagerFactorySingleton;

public class MessageDao {

    protected final EntityManagerFactory entityManagerFactory;

    /**
     * Standard constructor
     */
    public MessageDao() {
        this.entityManagerFactory = EntityManagerFactorySingleton.getFactory();
    }

    /**
     * Can use a custom EntityManagerFactory for unit testing
     * 
     * @param entityManagerFactory
     *            The factory to use to get sessions
     */
    public MessageDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public Message save(Message Message) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Message savedMessage = entityManager.merge(Message);
        transaction.commit();
        entityManager.close();
        return savedMessage;
    }

    public Message getMessageById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Message> query = entityManager.createQuery("from Message m where m.messageId = :id", Message.class);
        query.setParameter("id", id);
        Message message = query.getSingleResult();

        transaction.commit();
        entityManager.close();
        return message;
    }

    public void notify(Member member, String message) {
        Member intramurals = (Member) new MemberDao().getMemberByUsername("Intramurals");
        save(new Message(message, "", intramurals, member).setSent(true));
    }
}
