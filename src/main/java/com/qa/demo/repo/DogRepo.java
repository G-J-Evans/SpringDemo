package com.qa.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qa.demo.domain.Dog;

@Repository
public interface DogRepo extends JpaRepository<Dog, Integer> {
	
	// Spring auto-generates stand CRUD
	
	/* Additional methods can be created
		How to name 
		https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.namespace-reference
	*/
	List<Dog> findByNameIgnoreCase(String name);
	List<Dog> findByNumberOfLegs(Integer numberOfLegs);
	List<Dog> findByBreedIgnoreCase(String breed);
	Integer countBy();
	
	// Used for obtaining a random result
	@Query(value = "SELECT * FROM dog ORDER BY id LIMIT :n, 1", nativeQuery = true)
	Dog findNthPlusOneDogOrderByIdAsc(@Param("n") Integer n);
}