package com.qa.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.demo.domain.Dog;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // runs the test on a random port to avoid conflict
@AutoConfigureMockMvc //sets up the MockMvc object
@Sql(scripts = {"classpath:dog-schema.sql", "classpath:dog-data.sql"}, executionPhase=ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class DogControllerIntegrationTest {

	@Autowired // pull the MockMvc object from the context
	private MockMvc mvc; // class that performs the request(auto postman)
	
	@Autowired
	private ObjectMapper mapper; // java <-> JSON converter that spring uses
	
	@Test
	void testCreate() throws Exception {
		// make object to create
		Dog testDog = new Dog(null, "Jim", "hound", 4);
		// Turn object to JSON
		String testDogJSON = this.mapper.writeValueAsString(testDog);
		// Creates request for object to be created
		RequestBuilder request = post("/create").contentType(MediaType.APPLICATION_JSON).content(testDogJSON);
		
		// make object to test against
		Dog testCreatedDog = new Dog(2, "Jim", "hound", 4);
		// Turn object to string
		String testCreatedDogAsJSON = this.mapper.writeValueAsString(testCreatedDog);
		
		// return we want
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testCreatedDogAsJSON);
		
		// checks the input = correct status, and correct body
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testGetAll() throws Exception {
		// Creates request for getAll
		RequestBuilder request = get("/getAll");
		
		// List to check return against
		List<Dog> testDogs = List.of(new Dog(1, "Jimmy", "Hound", 4));
		// Turns list to JSON
		String testDogsJSON = this.mapper.writeValueAsString(testDogs);
		
		// return we want
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testDogsJSON);
		
		// the check
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testgetOne() throws Exception {
		// Creates request for getOne (by id)
		RequestBuilder request = get("/get/1");
		
		// DogJSON to check against
		String testDogJSON = this.mapper.writeValueAsString(new Dog(1, "Jimmy", "Hound", 4));
		
		// return we want
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testDogJSON);
		
		// the check
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
	@Test
	void testReplace() throws Exception {
		// make dogJSON to replace with
		String testDogJSON = this.mapper.writeValueAsString(new Dog(1 , "Jim", "hound", 4));
		
		// creates request for update (by id)
		RequestBuilder request = put("/replace/1").contentType(MediaType.APPLICATION_JSON).content(testDogJSON);
		
		// return we want
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(testDogJSON);
		
		// the check
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testGetByName() throws Exception {
		// Creates request for getOne (by Name)
		RequestBuilder request = get("/getByName/Jimmy");
		
		// DogListJSON to check against
		String testDogJSON = this.mapper.writeValueAsString(List.of(new Dog(1, "Jimmy", "Hound", 4)));
		
		// return we want
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testDogJSON);
		
		// the check
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testGetByBreed() throws Exception {
		// Creates request for getOne (by Breed)
		RequestBuilder request = get("/getByBreed/Hound");
		
		// DogListJSON to check against
		String testDogJSON = this.mapper.writeValueAsString(List.of(new Dog(1, "Jimmy", "Hound", 4)));
		
		// return we want
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testDogJSON);
		
		// the check
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testGetByNumberOfLegs() throws Exception {
		// Creates request for getOne (by NumberOfLegs)
		RequestBuilder request = get("/getByNumberOfLegs/4");
		
		// DogListJSON to check against
		String testDogJSON = this.mapper.writeValueAsString(List.of(new Dog(1, "Jimmy", "Hound", 4)));
		
		// return we want
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testDogJSON);
		
		// the check
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testGetRandom() throws Exception {
		// Create request for get random
		RequestBuilder request = get("/get/random");
		
		// JSON to test against??
		String testDogJSON = this.mapper.writeValueAsString(new Dog(1, "Jimmy", "Hound", 4));

		// return we want 
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testDogJSON);
		
		// the check
		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testRemove() throws Exception {
		// creates request for remove
		RequestBuilder request = delete("/remove/1");
		
		// return we want
		ResultMatcher checkStatus = status().isNoContent();
		
		// the check
		this.mvc.perform(request).andExpect(checkStatus);
	}
}
