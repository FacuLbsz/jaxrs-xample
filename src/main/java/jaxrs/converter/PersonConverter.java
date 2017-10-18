package jaxrs.converter;

import java.util.ArrayList;
import java.util.List;

import jaxrs.entity.PersonEntity;
import jaxrs.model.PersonModel;

/**
 * Convertidor de modelos a entidades.
 * 
 * @author Facundo-Zenbook
 *
 */
public class PersonConverter {

	/**
	 * Convierte un modelo en una entidad.
	 * 
	 * @param personModel
	 *            modelo base
	 * @return entidad
	 */
	public PersonEntity personModelToEntity(PersonModel personModel) {
		if (personModel == null) {
			return null;
		}
		return new PersonEntity(0, personModel.getForename(), personModel.getLastname(), personModel.getAge());
	}

	/**
	 * Convierte una entidad en un modelo.
	 * 
	 * @param personEntity
	 *            entidad base
	 * @return modelo
	 */
	public PersonModel personEntityToModel(PersonEntity personEntity) {
		if (personEntity == null) {
			return null;
		}
		return new PersonModel(personEntity.getId(), personEntity.getForename(), personEntity.getLastname(), personEntity.getAge());
	}

	/**
	 * Convierte una lista de entidades en una lista de modelos.
	 * 
	 * @param entities
	 *            entidades
	 * @return lista de modelos
	 */
	public List<PersonModel> convertList(List<PersonEntity> entities) {
		List<PersonModel> models = new ArrayList<PersonModel>();
		for (PersonEntity entity : entities) {
			models.add(personEntityToModel(entity));
		}
		return models;
	}

}
