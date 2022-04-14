package com.qa.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.demo.domain.Dog;
import com.qa.demo.service.DogService;

@RestController
public class DogController {
	
	private DogService service;
	
	@Autowired //tells spring to fetch the DogService from the context
	public DogController(DogService service) {
		super();
		this.service = service;
	}
	
	// --- CRUD functionality ---
	// ResponseEntity is an extension of HttpEntity that represents a HTTP response including status code, headers and body
	
	//create
	@PostMapping("/create") // 201 - CREATED
	public ResponseEntity<Dog> createDog(@RequestBody Dog dog) {
			return new ResponseEntity<>(this.service.create(dog), HttpStatus.CREATED);
	}
	
	//read all
	@GetMapping("/getAll") // 200 - OK
	public ResponseEntity<List<Dog>>  getAll() {
			return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
	}
	
	//read one by id
	@GetMapping("/get/{id}") // 200 - OK
	public ResponseEntity<Dog> getOne(@PathVariable Integer id) {
			return new ResponseEntity<>(this.service.getOne(id), HttpStatus.OK);
	}
	
	//update
	@PutMapping("/replace/{id}") // 202 - ACCEPTED
	public ResponseEntity<Dog> replace(@PathVariable Integer id, @RequestBody Dog dog) {
			return new ResponseEntity<>(this.service.replace(id, dog), HttpStatus.ACCEPTED);
	}
	
	//delete
	@DeleteMapping("/remove/{id}") // 204 - NO CONTENT
	public ResponseEntity<?> delete(@PathVariable Integer id) {
			this.service.remove(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	//read by name
	@GetMapping("/getByName/{name}") // 200 - OK
	public ResponseEntity<List<Dog>> getByName(@PathVariable String name) {
		return new ResponseEntity<>(this.service.getDogsByName(name), HttpStatus.OK);
	}
	
	//read by breed
	@GetMapping("/getByBreed/{breed}") // 200 - OK
	public ResponseEntity<List<Dog>> getByBreed(@PathVariable String breed) {
		return new ResponseEntity<>(this.service.getDogsByBreed(breed), HttpStatus.OK);
	}
	
	//read by numberOfLegs // 200 - OK
	@GetMapping("/getByNumberOfLegs/{numberOfLegs}")
	public ResponseEntity<List<Dog>> getByNumberOfLegs(@PathVariable Integer numberOfLegs) {
		return new ResponseEntity<>(this.service.getDogsByNumberOfLegs(numberOfLegs), HttpStatus.OK);
	}
	
	//read by random
	@GetMapping("/get/random") // 200 - OK
	public ResponseEntity<Dog> getRandom() {
		return new ResponseEntity<>(this.service.getRandom(), HttpStatus.OK);
	}
}