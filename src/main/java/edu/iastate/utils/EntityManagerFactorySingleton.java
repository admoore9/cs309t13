package edu.iastate.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
