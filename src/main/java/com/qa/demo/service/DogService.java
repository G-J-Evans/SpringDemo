package com.qa.demo.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.qa.demo.domain.Dog;
import com.qa.demo.repo.DogRepo;

@Service // stores the main business logic of the application
public class DogService implements ServiceIF<Dog>{
	
	private DogRepo repo;
	
	@Autowired
	public DogService(DogRepo repo) {
		super();
		this.repo = repo;
	}
	
	// --- CRUD for service ---
	
	//create // INSERT INTO Dog
	public Dog create(Dog dog) {
		return this.repo.save(dog);
	}
	
	//readAll // SELECT * FROM Dog
	public List<Dog> getAll() {
		return this.repo.findAll();
	}
	
	//readOne // SELECT * FROM Dog WHERE ID =
	public Dog getOne(Integer id) {
		return this.repo.findById(id).get();
	}
	
	//replace // UPDATE Dog SET name = , breed =, number_of_legs = WHERE id = 
	public Dog replace(Integer id, Dog newDog) {
		Dog existing = this.repo.findById(id).get();
		existing.setBreed(newDog.getBreed());
		existing.setName(newDog.getName());
		existing.setNumberOfLegs(newDog.getNumberOfLegs());
		return this.repo.save(existing);
	}
	
	//delete // DELETE FROM Dog WHERE id =
	public void remove(Integer id) {
		this.repo.deleteById(id);
	}
	
	// SELECT * FROM Dog WHERE name =
	public List<Dog> getByName(String name) {
		return this.repo.findByNameIgnoreCase(name);
	}
	
	// SELECT * FROM Dog WHERE breed = 
	public List<Dog> getByBreed(String breed) {
		return this.repo.findByBreedIgnoreCase(breed);
	}
	
	// SELECT * FROM Dog WHERE numberOfLegs =
	public List<Dog> getByNumberOfLegs(Integer numberOfLegs) {
		return this.repo.findByNumberOfLegs(numberOfLegs);
	}
	
	// SELECT * FROM Dog WHERE (random) // Not real MySQL :)
	public Dog getRandom() {
		Integer noOfDogs = this.repo.countBy();
		if(noOfDogs == 0) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Database has no entries");
		}
		return this.repo.findNthPlusOneDogOrderByIdAsc(new Random().nextInt(noOfDogs));
	}
}
