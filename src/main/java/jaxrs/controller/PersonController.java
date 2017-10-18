package jaxrs.controller;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import jaxrs.model.PersonModel;
import jaxrs.service.PersonService;

/**
 * Controlador de personas.
 * 
 * @author Facundo-Zenbook
 *
 */
@Path("/personas")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class PersonController {

	private static final Logger LOGGER = Logger.getLogger(PersonController.class);
	private PersonService personService;

	public PersonController() {
		super();
		this.personService = PersonService.getInstance();
	}

	/**
	 * Obtiene una persona segun su id.
	 * 
	 * @param id
	 *            id de la persona a obtener
	 * @return persona obtenida y {@link Status#OK} <br>
	 *         en caso de que el id no sea de tipo numerico {@link Status#BAD_REQUEST} <br>
	 *         en caso de que no exista una persona con el id solicitado {@link Status#NOT_FOUND}
	 */
	@GET
	@Path("/{id}")
	public Response read(@PathParam("id") String id) {
		LOGGER.info("METHOD INVOKE: read PARAMS: " + id);
		try {
			Integer.parseInt(id);
		} catch (NumberFormatException e) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		PersonModel byId = personService.read(Integer.parseInt(id));
		if (byId == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.status(Status.OK).entity(byId).build();

	}

	/**
	 * Obtiene una lista de personas.
	 * 
	 * @return lista de personas existentes {@link Status#OK}
	 */
	@GET
	@Path("/")
	public Response fetchAll() {
		LOGGER.info("METHOD INVOKE: fetchAll");

		return Response.status(Status.OK).entity(personService.fetchAll()).build();
	}

	/**
	 * Crea una entidad persona.
	 * 
	 * @return persona creada con su respectivo identificador {@link Status#CREATED}
	 */
	@POST
	@Path("/")
	public Response create(PersonModel personModel) {
		LOGGER.info("METHOD INVOKE: create PARAMS: " + personModel);

		PersonModel create = personService.create(personModel);

		return Response.status(Status.CREATED).entity(create).build();
	}

	/**
	 * Elimina una persona segun su id.
	 * 
	 * @param id
	 *            id de la persona a eliminar
	 * @return persona eliminada correctamente {@link Status#OK} <br>
	 *         en caso de que el id no sea de tipo numerico {@link Status#BAD_REQUEST} <br>
	 *         en caso de que no exista una persona con el id solicitado {@link Status#NOT_FOUND}
	 */
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") String id) {
		LOGGER.info("METHOD INVOKE: delete PARAMS: " + id);

		try {
			Integer.parseInt(id);
		} catch (NumberFormatException e) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		try {
			personService.delete(Integer.parseInt(id));
		} catch (EntityNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}

		return Response.status(Status.OK).build();

	}

}
