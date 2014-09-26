package edu.iastate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import edu.iastate.models.Tournament;
import edu.iastate.utils.HibernateUtil;

public class TournamentDao {

    private final SessionFactory sessionFactory;

    /**
     * Standard constructor
     */
    public TournamentDao() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    /**
     * Can use a custom SessionFactory for unit testing
     * 
     * @param sessionFactory The factory to use to get sessions
     */
    public TournamentDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Tournament> getTournaments() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Tournament> tournaments = (List<Tournament>) session.createSQLQuery("SELECT * FROM Tournament").list();
        transaction.commit();
        return tournaments;
    }
}
