package jaxrs.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import jaxrs.util.SessionUtil;

/**
 * Repositorio abstracto de entidades.
 * 
 * @author Facundo-Zenbook
 *
 * @param <T>
 *            tipo de entidad
 */
public abstract class EntityRepository<T> {

	/**
	 * Crea una entidad.
	 * 
	 * @param entityToAdd
	 *            entidad a crear
	 * @return identificador de la entidad creada
	 */
	public int create(T entityToAdd) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		int id = (Integer) session.save(entityToAdd);
		tx.commit();
		session.close();

		return id;
	}

	/**
	 * Obtiene una entidad segun su id.
	 * 
	 * @param id
	 *            identificador de la entidad a obtener
	 * @return entidad obtenida segun id o {@code null} en caso de que no exista la entidad.
	 */
	public T read(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		T result = read(id, session);
		tx.commit();
		session.close();

		return result;
	}

	private T read(int id, Session session) {
		Query<T> createCriteria = session.createQuery("Select e From " + getEntityClass().getName() + " e WHERE e.id = :id");
		createCriteria.setParameter("id", id);

		T result = createCriteria.uniqueResult();
		return result;
	}

	/**
	 * Obtiene un listado de entidades existentes.
	 * 
	 * @return listado de entidades existentes
	 */
	public List<T> fetchAll() {

		List<T> fetchAll = new ArrayList<T>();

		Session session = SessionUtil.getSession();

		Query<T> createCriteria = session.createQuery("From " + getEntityClass().getName());

		fetchAll = createCriteria.list();
		session.close();

		return fetchAll;
	}

	/**
	 * Elimina una entidad segun su id.
	 * 
	 * @param id
	 *            identificador de la entidad a eliminar.
	 * @throws EntityNotFoundException
	 *             en caso de no existir la entidad a eliminar
	 */
	public void delete(int id) throws EntityNotFoundException {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		T read = read(id, session);
		if (read == null) {
			tx.commit();
			session.close();
			throw new EntityNotFoundException("No se ha encontrado la entidad que se requiere eliminar.");
		}
		session.delete(read);
		tx.commit();
		session.close();
	}

	/**
	 * Obtener la clase correspondiente a la entidad.
	 * 
	 * @return la clase.
	 */
	protected abstract Class<?> getEntityClass();

}
