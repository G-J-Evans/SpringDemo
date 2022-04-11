package com.qa.demo.domain;

public class Dog {
	private Integer id;
	private String name;
	private String breed;
	private Integer numberOfLegs;
	public Dog(Integer id, String name, String breed, Integer numberOfLegs) {
		super();
		this.id = id;
		this.name = name;
		this.breed = breed;
		this.numberOfLegs = numberOfLegs;
	}
	public Dog() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public Integer getNumberOfLegs() {
		return numberOfLegs;
	}
	public void setNumberOfLegs(Integer numberOfLegs) {
		this.numberOfLegs = numberOfLegs;
	}
	@Override
	public String toString() {
		return "Dog [id=" + id + ", name=" + name + ", breed=" + breed + ", numberOfLegs=" + numberOfLegs + "]";
	}
}
