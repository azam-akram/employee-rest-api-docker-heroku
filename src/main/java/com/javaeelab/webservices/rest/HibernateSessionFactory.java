package com.javaeelab.webservices.rest;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            try {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            } catch (Throwable e) {
                System.err.println("Failed to create SessionFactory" + e);
                throw new ExceptionInInitializerError(e);
            }
        }
        return sessionFactory;
    }
}