package com.collab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.collab.dao.PersonDao;
import com.collab.model.Person;
import com.collab.model.Error;
@Controller
public class PersonController {
	@Autowired
private PersonDao personDao;
	
@RequestMapping(value="/person",method=RequestMethod.POST)
//RequestBody - client to server - JSON to java object
//ResponseBody - server to client - Java object to JSON
public @ResponseBody Person savePerson(@RequestBody Person person){
	return personDao.addPerson(person);
}

@RequestMapping(value="/person/{personId}",method=RequestMethod.GET)
// ? - Any Type of Object 
public ResponseEntity<?> getPerson(@PathVariable(value="personId") int personId){
	Person person=personDao.getPerson(personId);
	if(person==null){
		Error error=new Error(1,"Person Id [" + personId + "]" + " doesn't exists");
		return new ResponseEntity<Error>(error,HttpStatus.NOT_FOUND);
	}
	return new ResponseEntity<Person>(person,HttpStatus.OK);
	
}

@RequestMapping(value="/person",method=RequestMethod.PUT)
public ResponseEntity<?> updatePerson(@RequestBody Person person){
	Person currentPerson=personDao.getPerson(person.getId());
	if(currentPerson==null){
		Error error=new Error(1,"Person Id [" + person.getId() + "]" + " doesn't exists");
		return new ResponseEntity<Error>(error,HttpStatus.NOT_FOUND);
	}
	Person updatedPerson=personDao.updatePerson(person);
	return new ResponseEntity<Person>(updatedPerson,HttpStatus.OK);
	
	
}

@RequestMapping(value="/person/{id}",method=RequestMethod.DELETE)
public ResponseEntity<?> deletePerson(@PathVariable int id){
	Person person=personDao.getPerson(id);
	if(person==null){
		Error error=new Error(1,"Person Id [" + id + "]" + " doesn't exists");
		return new ResponseEntity<Error>(error,HttpStatus.NOT_FOUND);
	}
	personDao.deletePerson(id);
	return new ResponseEntity<Void>(HttpStatus.OK);
}


}
