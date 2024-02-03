package com.consultations.patientsjee.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateSession {

    private static StandardServiceRegistry registry = null;
    private static volatile SessionFactory sessionFactory = null;


    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {

            synchronized (HibernateSession.class) {
                if (sessionFactory == null) {
                    registry = new StandardServiceRegistryBuilder().configure().build();
                    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
                }
            }


        }
        return sessionFactory;
    }

    public static void closeSessionFactory(){
        if (registry != null){
            StandardServiceRegistryBuilder.destroy(registry);
            sessionFactory = null;
        }
    }
}
