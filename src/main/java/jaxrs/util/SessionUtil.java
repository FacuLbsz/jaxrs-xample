package jaxrs.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jaxrs.entity.PersonEntity;

/**
 * Util de session de Hibernate.
 * 
 * @author Facundo-Zenbook
 *
 */
public class SessionUtil {

	private static SessionUtil instance = new SessionUtil();
	private SessionFactory sessionFactory;

	/**
	 * Obtiene una instancia de SessionUtil.
	 * 
	 * @return instancia
	 */
	public static SessionUtil getInstance() {
		return instance;
	}

	private SessionUtil() {
		Configuration configuration = new Configuration().setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver")
				.setProperty("hibernate.connection.url", "jdbc:hsqldb:mem:TEST_RUN;sql.syntax_mys=true")
				.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect").setProperty("hibernate.hbm2ddl.auto", "create")
				.setProperty("hibernate.show_sql", "true").addAnnotatedClass(PersonEntity.class);

		sessionFactory = configuration.buildSessionFactory();
	}

	/**
	 * Obtiene una nueva session de hibernate.
	 * 
	 * @return session de hibernate
	 */
	public static Session getSession() {
		Session session = getInstance().sessionFactory.openSession();

		return session;
	}
}