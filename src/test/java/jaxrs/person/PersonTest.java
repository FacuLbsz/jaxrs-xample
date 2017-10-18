package jaxrs.person;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jaxrs.controller.PersonController;
import jaxrs.model.PersonModel;

/**
 * Test de controlador de personas. Utilizando como base JerseyTest para crear e iniciar el servicio {@link PersonController}.
 * 
 * @author Facundo-Zenbook
 *
 */
public class PersonTest extends JerseyTest {
	private static final String PERSONAS_PATH = "/personas";

	private static PersonModel personModel1;
	private static PersonModel personModel2;

	private static boolean setup;

	@Override
	protected Application configure() {
		return new ResourceConfig(PersonController.class);
	}

	@Before
	public void setUpt() {
		if (!setup) {
			createInitialData();
			setup = true;
		}
	}

	public void createInitialData() {

		System.out.println("***********CREATING INITIAL DATA************");

		PersonModel person1 = new PersonModel(0, "forename", "lastname", 30);
		Response response = target(PERSONAS_PATH).request().post(Entity.json(person1));

		personModel1 = response.readEntity(PersonModel.class);

		PersonModel person2 = new PersonModel(0, "forename2", "lastname2", 32);
		response = target(PERSONAS_PATH).request().post(Entity.json(person2));

		personModel2 = response.readEntity(PersonModel.class);

	}

	@Test
	public void testFetchAll() throws Exception {
		Response response = target(PERSONAS_PATH).request().get();

		Assert.assertEquals(response.getStatus(), 200);

		List<PersonModel> personModels = response.readEntity(new GenericType<List<PersonModel>>() {
		});
		Assert.assertTrue(personModels.size() == 2);
		Assert.assertTrue(personModels.contains(personModel1));
		Assert.assertTrue(personModels.contains(personModel2));
	}

	@Test
	public void testGetById() {
		Response response = target(PERSONAS_PATH + "/" + personModel1.getId()).request().get();

		Assert.assertEquals(response.getStatus(), 200);

		PersonModel personModel = response.readEntity(PersonModel.class);

		Assert.assertEquals(personModel.getId(), personModel1.getId());
		Assert.assertEquals(personModel.getForename(), "forename");
		Assert.assertEquals(personModel.getLastname(), "lastname");
		Assert.assertEquals(personModel.getAge(), 30);
	}

	@Test
	public void testGetByIdANonExistingPerson() {
		Response response = target(PERSONAS_PATH + "/" + 0).request().get();

		Assert.assertEquals(response.getStatus(), 404);
	}

	@Test
	public void testGetByInvalidId() {
		Response response = target(PERSONAS_PATH + "/a").request().get();

		Assert.assertEquals(response.getStatus(), 400);
	}

}
