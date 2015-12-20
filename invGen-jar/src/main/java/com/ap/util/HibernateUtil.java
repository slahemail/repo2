package com.ap.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * HibernateUtil - Hibernate session manager and tools. 
 * 
 * @version 1.0
 * @author A
 * @date   13/12/2015
 * 
 */
public class HibernateUtil {
  
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;

	/**
	 * createSessionFactory - Create the session factory and serviceRegistry.
	 *
	 * @return 
	 * @param 
	 */
	public static SessionFactory createSessionFactory() {
	    Configuration configuration = new Configuration();
	    configuration.configure();
	    serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
	            configuration.getProperties()).build();
	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	    return sessionFactory;
	}

	 public static SessionFactory getSessionFactory() {
		 if (sessionFactory == null) {
			 sessionFactory = createSessionFactory(); 
		 }
	     return sessionFactory;
	 }

	 public static void shutdown() {
	 // Close caches and connection pools
	 getSessionFactory().close();
	 }
  
}