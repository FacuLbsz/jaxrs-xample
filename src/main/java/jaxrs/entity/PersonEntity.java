package jaxrs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidad de Persona.
 * 
 * @author Facundo-Zenbook
 *
 */
@Entity
@Table(name = PersonEntity.NAME)
public class PersonEntity {

	public static final String NAME = "person";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String forename;
	private String lastname;
	private int age;

	public PersonEntity() {
	}

	public PersonEntity(int id, String forename, String lastname, int age) {
		super();
		this.id = id;
		this.forename = forename;
		this.lastname = lastname;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
