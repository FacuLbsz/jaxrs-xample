package jaxrs.repository;

import jaxrs.entity.PersonEntity;

/**
 * Repositorio de Personas.
 * 
 * @author Facundo-Zenbook
 *
 */
public class PersonRepository extends EntityRepository<PersonEntity> {

	@Override
	protected Class<?> getEntityClass() {
		return PersonEntity.class;
	}
}
