package edu.iastate.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Singleton class for getting the EntityManagerFactory for the application so
 * there's only one factory for the application.
 *
 * @author brianshannan
 */
public class EntityManagerFactorySingleton {

    private static EntityManagerFactory factory = makeEntityManagerFactory();

    private EntityManagerFactorySingleton() {}

    private static EntityManagerFactory makeEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("cs309t13");
    }

    public static EntityManagerFactory getFactory() {
        return factory;
    }
}
