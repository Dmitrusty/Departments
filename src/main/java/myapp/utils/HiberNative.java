package myapp.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HiberNative {
    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static Session getSession(){
        return sessionFactory.openSession();
    }
}
