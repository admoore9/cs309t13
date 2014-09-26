package edu.iastate.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import edu.iastate.models.Game;
import edu.iastate.models.Player;
import edu.iastate.models.Team;
import edu.iastate.models.Tournament;

public class HibernateUtil {

    private HibernateUtil() {
        // Prevent instantiation
    }

    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Tournament.class);
        configuration.addAnnotatedClass(Game.class);
        configuration.addAnnotatedClass(Team.class);
        configuration.addAnnotatedClass(Player.class);

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());

        SessionFactory factory = configuration.buildSessionFactory(builder.build());
        return factory;
    }
}
