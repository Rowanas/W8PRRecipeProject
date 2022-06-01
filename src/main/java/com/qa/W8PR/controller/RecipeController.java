package com.qa.W8PR.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.qa.W8PR.domain.Recipe;
import com.qa.W8PR.services.RecipeService;

@RestController
@CrossOrigin
@RequestMapping("/recipes")
public class RecipeController {

	private RecipeService service;

	public RecipeController(RecipeService service) {
		this.service = service;
	}
// Temporary database for testing syntax
//	private List<Recipe> recipes = new ArrayList<>();

	@PostMapping("/create")
	public ResponseEntity<Recipe> create(@RequestBody Recipe recipe) {
		return new ResponseEntity<Recipe>(service.create(recipe), HttpStatus.CREATED);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Recipe>> getAll() {
		return new ResponseEntity<List<Recipe>>(service.getAll(), HttpStatus.OK);
	}
	
// I'm keeping these nubbins, but when I'm performing these functions in my webapp,
//	I might just do them in javascript off a basic get request and display just what I want in JS. 
//	@GetMapping("/getRecipeByName/{recipeName}")
//	public ResponseEntity <Recipe> getRecipeByName(@PathVariable String recipeName) {
//		return new ResponseEntity<Recipe>(service.getRecipeByName(recipeName), HttpStatus.OK);
//	}
//	
//	@GetMapping("getDietFriendly/{dietFriendly}")
//	public ResponseEntity<List<Recipe>> getDietFriendly(@PathVariable boolean dietFriendly) {
//		return new ResponseEntity<List<Recipe>>(service.getDietFriendly(dietFriendly), HttpStatus.OK);
//	}
//	
	@GetMapping("/getById/{id}")
	public ResponseEntity<Recipe> getById(@PathVariable long id) {
		return new ResponseEntity<Recipe>(service.getById(id), HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Recipe> update(@PathVariable long id, @RequestBody Recipe recipe) {
		return new ResponseEntity<Recipe>(service.update(id, recipe), HttpStatus.OK);
	}

	// changed to boolean output to match delete service method.
	// can do a ternary if
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean>delete(@PathVariable long id) {
		return service.delete(id) ? new ResponseEntity<Boolean>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
