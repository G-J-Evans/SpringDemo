package com.qa.demo.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	//readAll // SELECT * FROM Person
	public List<Dog> getAll() {
		return this.repo.findAll();
	}
	
	//readOne // SELECT * FROM Person WHERE ID =
	public Dog getOne(Integer id) {
		return this.repo.findById(id).get();
	}
	
	//replace 
	public Dog replace(Integer id, Dog newDog) {
		Dog existing = this.repo.findById(id).get();
		existing.setBreed(newDog.getBreed());
		existing.setName(newDog.getName());
		existing.setNumberOfLegs(newDog.getNumberOfLegs());
		return this.repo.save(existing);
	}
	
	//delete // DELETE FROM PERSON WHERE ID =
	public void remove(Integer id) {
		this.repo.deleteById(id);
	}
	
	// SELECT * FROM Dog WHERE name =
	public List<Dog> getDogsByName(String name) {
		return this.repo.findByNameIgnoreCase(name);
	}
	
	// SELECT * FROM Dog WHERE breed = 
	public List<Dog> getDogsByBreed(String breed) {
		return this.repo.findByBreedIgnoreCase(breed);
	}
	
	// SELECT * FROM Dog WHERE numberOfLegs =
	public List<Dog> getDogsByNumberOfLegs(Integer numberOfLegs) {
		return this.repo.findByNumberOfLegs(numberOfLegs);
	}
	
	// SELECT * FROM Dog WHERE = random
	public Dog getRandom() {
		// returns number of dog
		Integer noOfDogs = this.repo.countBy();
		// Checks there is data
		if(noOfDogs == 0) {
			// throw some Exception
			return new Dog();
		}
		// gets a random number n between 0 and noOfDogs -1, returns (n+1)th dog
		return this.repo.findNthPlusOneDogOrderByIdAsc(new Random().nextInt(noOfDogs));
	}
}
