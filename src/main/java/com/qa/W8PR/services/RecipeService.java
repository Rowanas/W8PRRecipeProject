package com.qa.W8PR.services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.qa.W8PR.domain.Recipe;
import com.qa.W8PR.exceptions.RecipeNotFoundException;
import com.qa.W8PR.exceptions.RecipeNotFoundExceptionWithName;
import com.qa.W8PR.repositories.RecipeRepo;

@Service
public class RecipeService {

	// Temporary database for testing syntax
	//	private List<Recipe> recipes = new ArrayList<>();
	
	private RecipeRepo repo;
	
	public RecipeService(RecipeRepo repo)	{
		this.repo = repo;
	}

	public Recipe create(Recipe newRecipe) {
		return repo.saveAndFlush(newRecipe);
	}

	public List<Recipe> getAll() {
		return repo.findAll();
	}
	
	//custom query
	public Recipe getRecipeByName(String recipeName)	{
		return repo.findRecipeByRecipeName(recipeName).orElseThrow(() -> new RecipeNotFoundExceptionWithName(recipeName));
	}
	
	//custom query
	public List<Recipe> getDietFriendly(boolean dietFriendly)	{
		return repo.findRecipeByDietFriendly(dietFriendly);
	}
	
	// We could try a throw nullPointerException, but we'll use optionals instead
	//using new orElseThrow without specification, as proof of learning of two different exception
	//types
	public Recipe getById(long id) {
		return repo.findById(id).orElseThrow(RecipeNotFoundException::new);
		// here we can catch and force the user to pick the right thing
	}

	// Update -> Put Request
	public Recipe update(long id, Recipe recipe) {
		Recipe current = repo.findById(id).get();
		current.setRecipeName(recipe.getRecipeName());
		current.setIngredients(recipe.getIngredients());
		current.setDietFriendly(recipe.isDietFriendly());
		return repo.saveAndFlush(current);
	}
	
	// Delete -> Delete Request
	//changed to boolean to return true or false. !repo. normally returns false if does not exist
	//but much nicer human-logically to invert it, so returns true if the id has been deleted
	public boolean delete(long id) {
		repo.deleteById(id);
		return !repo.existsById(id);
	}
}
//for reference with params
	// Update -> Put Request with params
//	public Recipe updateWithParam(long id, Recipe recipe) {

//	}


