package edu.iastate.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseUtil {

    private DatabaseUtil() {
        // Prevent instantiation
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cs309t13");
        return entityManagerFactory;
    }
}
