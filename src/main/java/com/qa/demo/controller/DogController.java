package com.qa.demo.controller;

import java.util.ArrayList;
import java.util.List;

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

@RestController
public class DogController {

	// just for storing data, we will delete after we start using a db
	private List<Dog> dogs = new ArrayList<>();
	
	// --- CRUD functionality ---
	// ResponseEntity is an extension of HttpEntity that represents a HTTP response including status code, headers and body
	
	//create
	@PostMapping("/create") // 201 - created
	public ResponseEntity<Dog> createDog(@RequestBody Dog dog) {
		try {
			this.dogs.add(dog);
			return new ResponseEntity<Dog>(this.dogs.get(this.dogs.size()-1), HttpStatus.CREATED);
		} catch(Error e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	//read all
	@GetMapping("/getAll") // 200 - ok
	public ResponseEntity<List<Dog>>  getAll() {
		try {
			return new ResponseEntity<List<Dog>>(this.dogs, HttpStatus.OK);
		} catch(Error e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//read one
	@GetMapping("/get/{id}") // 200 - ok
	public ResponseEntity<Dog> getDog(@PathVariable Integer id) {
		try {
			return new ResponseEntity<Dog>(dogs.get(id), HttpStatus.OK);
		} catch(Error e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//update
	@PutMapping("/replace/{id}") // 202 - accepted
	public ResponseEntity<Dog> replaceDog(@PathVariable Integer id, @RequestBody Dog dog) {
		try {
			return new ResponseEntity<Dog>(dogs.set(id, dog), HttpStatus.ACCEPTED);
		} catch(Error e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//delete
	@DeleteMapping("/remove/{id}") // No content
	public ResponseEntity<?> deleteDog(@PathVariable Integer id) {
		try {
			this.dogs.remove(id.intValue());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch(Error e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}