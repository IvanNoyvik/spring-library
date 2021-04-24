package by.gomel.noyvik.library.persistance.connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class JpaEntityManagerFactoryUtil {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY;


    static {
        ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("library");
    }

    private JpaEntityManagerFactoryUtil() {

    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return ENTITY_MANAGER_FACTORY;
    }

    public static EntityManager getEntityManager() {
        return ENTITY_MANAGER_FACTORY.createEntityManager();
    }
}
