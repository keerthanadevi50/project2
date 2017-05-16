package com.collab.dao;

import com.collab.model.Person;

public interface PersonDao {
	Person addPerson(Person person);
	Person getPerson(int id);
	Person updatePerson(Person person);
	void deletePerson(int id);
	

}
