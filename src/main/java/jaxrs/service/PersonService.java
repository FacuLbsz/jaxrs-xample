package jaxrs.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import jaxrs.converter.PersonConverter;
import jaxrs.model.PersonModel;
import jaxrs.repository.PersonRepository;

/**
 * Servicio de personas.
 * 
 * @author Facundo-Zenbook
 *
 */
public class PersonService {

	private static PersonService instance;
	private PersonConverter personConverter;
	private PersonRepository personRepository;

	private PersonService() {
		personConverter = new PersonConverter();
		personRepository = new PersonRepository();
	}

	/**
	 * Obtiene la instancia del servicio de personas.
	 * 
	 * @return instancia del servicio de personas
	 */
	public static PersonService getInstance() {
		if (instance == null) {
			instance = new PersonService();
		}
		return instance;
	}

	/**
	 * Obtiene una persona segun su id.
	 * 
	 * @param id
	 *            identificador de la persona a obtener.
	 * @return persona obtenida o {@code null} en caso de que no exista la persona
	 */
	public PersonModel read(int id) {
		return personConverter.personEntityToModel(personRepository.read(id));
	}

	/**
	 * Obtiene una lista de personas existentes.
	 * 
	 * @return personas existentes
	 */
	public List<PersonModel> fetchAll() {
		return personConverter.convertList(personRepository.fetchAll());
	}

	/**
	 * Crea una nueva persona.
	 * 
	 * @param personModel
	 *            persona a crear
	 * @return persona creada con su identificador
	 */
	public PersonModel create(PersonModel personModel) {
		personModel.setId(personRepository.create(personConverter.personModelToEntity(personModel)));
		return personModel;
	}

	/**
	 * Elimina una persona segun su id.
	 * 
	 * @param id
	 *            identificador de la persona a eliminar
	 */
	public void delete(int id) throws EntityNotFoundException {
		personRepository.delete(id);
	}
}
