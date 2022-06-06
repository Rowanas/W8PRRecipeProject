package com.qa.W8PR.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//creating a table with spring. Doesn't need to be called, SpringApplication goes and hunts all of the springable stuff
//and pops it in your tables.
//Lombok used as proof of Lombok use. I used Lombok. Lombok config file excludes this from testing.
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

	@Id // makes this column the primary key you can put it in another non-numerical key!
	@GeneratedValue(strategy = GenerationType.IDENTITY) //longwinded way of saying "autoincrement"
	private long id;
	
	//may put "unique = true" again as a stretch goal
	//with functionality to kick a user to check an existing recipe if they attempt to create
	//another with the same name. Could do entirely front end though.
	@Column(nullable = false)
	private String recipeName; // recognises camelCase and split to become SQLable names
	
	@Column(nullable = false)
	private String ingredients;

	@Column
	private boolean dietFriendly;

	public Recipe(String recipeName, String ingredients, boolean dietFriendly) {
		super();
		this.recipeName = recipeName;
		this.ingredients = ingredients;
		this.dietFriendly = dietFriendly;
	}	
}