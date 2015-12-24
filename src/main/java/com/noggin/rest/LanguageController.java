package com.noggin.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.noggin.dao.repositories.ILanguage;
import com.noggin.models.Language;

@RestController
@RequestMapping(value = "/language")
public class LanguageController {
	
	@Autowired
	private ILanguage il;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<Language> getAll(){
		List<Language> languages = il.findAll();
		return languages;
	}
	
	@RequestMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Language> get(@PathVariable String id){
		Integer intId = null;
		Language language = null;
		try{
			intId = Integer.parseInt(id);
			language = il.findOne(intId);
		}catch(Exception e){
			return new ResponseEntity<Language>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Language>(language, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE, consumes = "application/json")
	public ResponseEntity<Language> delete(@PathVariable String id){
		Integer intId = null;
		try{
			intId = Integer.parseInt(id);
			il.delete(intId);
		}catch(Exception e){
			return new ResponseEntity<Language>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Language>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Language> update(@PathVariable String id, @RequestBody Language language){
		Integer intId = null;
		Language l = new Language();
		try{
			intId = Integer.parseInt(id);
			l = il.findOne(intId);
		}catch(Exception e){
			return new ResponseEntity<Language>(HttpStatus.BAD_REQUEST);
		}
		l.setName(language.getName());
		return new ResponseEntity<Language>(il.save(l), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Language> add(@RequestBody Language language){
		return new ResponseEntity<Language>(il.save(language), HttpStatus.OK);
	}
}
