package topica.linhnv5.java8.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * HibernateUtil to execute sql
 * @author ljnk975
 */
public class HibernateUtils {

	// Config file
	private static final String configFileName = "hibernate-mysql.cfg.xml";

	//
	private static Metadata metadata;
	private static SessionFactory sessionFactory;

	private static SessionFactory buildSessionFactory() {
		try {
			return getMetadata().getSessionFactoryBuilder().build();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Metadata getMetadata() {
		if (metadata == null) {
			try {
				// Create the ServiceRegistry from hibernate.cfg.xml
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure(configFileName).build();

				// Create a metadata sources using the specified service registry.
				metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
			} catch (Throwable ex) {
				System.err.println("Initial Metadata creation failed." + ex);
				throw new ExceptionInInitializerError(ex);
			}
		}
		return metadata;
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null)
			sessionFactory = buildSessionFactory();
		return sessionFactory;
	}

	public static void shutdown() {
		// Close caches and connection pools
		if (sessionFactory != null)
			sessionFactory.close();
	}

}