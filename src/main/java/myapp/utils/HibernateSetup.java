package myapp.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateSetup {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( "myapp.utils.entitymanager.jpa" );

    public static EntityManagerFactory getFactory() {
        return entityManagerFactory;
    }
}
