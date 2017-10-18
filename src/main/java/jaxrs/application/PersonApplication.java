package jaxrs.application;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.log4j.Logger;

/**
 * Aplicacion JAX-RS 2.0 de personas.
 * 
 * @author Facundo-Zenbook
 *
 */
@ApplicationPath("/")
public class PersonApplication extends Application {

	private static final Logger LOGGER = Logger.getLogger(PersonApplication.class);

	public PersonApplication() {
		LOGGER.info("Aplicacion inicializada.");
	}
}
