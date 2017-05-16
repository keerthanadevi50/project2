package com.collab.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person_batch15")
public class Person {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int id;
public String  Person;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getPerson() {
	return Person;
}
public void setPerson(String person) {
	Person = person;
}

}
	


