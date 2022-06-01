package com.qa.W8PR.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.W8PR.domain.Recipe;
import com.qa.W8PR.repositories.RecipeRepo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RecipeServiceTest {

	@Autowired // hooks up our service, finds mockbean below
	private RecipeService service;

	@MockBean
	private RecipeRepo repo;

	@Test 
	public void createTest() {
		Recipe newRecipeTest = new Recipe(1L, "Spagbol", "not much", true);
		Mockito.when(repo.saveAndFlush(newRecipeTest)).thenReturn(newRecipeTest);
		assertEquals(newRecipeTest, service.create(newRecipeTest)); 	//is this cheating? this feels like cheating.
		Mockito.verify(repo, Mockito.times(1)).saveAndFlush(newRecipeTest);
	}

	@Test
	public void getAllTest() {
		List<Recipe> testRecipeList = new ArrayList<>();
		testRecipeList.add(new Recipe(1L, "Spagbol", "not much", true));
		Mockito.when(repo.findAll()).thenReturn(testRecipeList);
		assertEquals(testRecipeList, service.getAll());
		Mockito.verify(repo, Mockito.times(1)).findAll();
	}

	@Test
	public void getRecipeByNameTest() {
		Recipe newRecipeTest = new Recipe(1L, "Spagbol", "not much", true);
		Mockito.when(repo.findRecipeByRecipeName("Spagbol")).thenReturn(Optional.of(newRecipeTest));
		assertEquals(newRecipeTest, service.getRecipeByName("Spagbol"));
		Mockito.verify(repo, Mockito.times(1)).findRecipeByRecipeName("Spagbol");
	}

	@Test
	public void getDietFriendlyTest() {
		List<Recipe> testRecipeList = new ArrayList<>();
		testRecipeList.add(new Recipe(1L, "Spagbol", "not much", true));
		Mockito.when(repo.findRecipeByDietFriendly(true)).thenReturn(testRecipeList);
		assertEquals(testRecipeList, service.getDietFriendly(true));
		Mockito.verify(repo, Mockito.times(1)).findRecipeByDietFriendly(true);
	}

	@Test
	public void getByIdTest() {
		Recipe newRecipeTest = new Recipe(1L, "Spagbol", "not much", true);
		
		Mockito.when(repo.findById(1L)).thenReturn(Optional.of(newRecipeTest));
		assertEquals(newRecipeTest, service.getById(1L));
		Mockito.verify(repo, Mockito.times(1)).findById(1L);
	}

	@Test
	public void updateTest() {
		Recipe newRecipeTest = new Recipe("Carbonara", "Carbon", false);
		Optional<Recipe> existingRecipe = Optional.of(new Recipe(1L, "Spagbol", "Spag, Bol", true));
		Recipe outputRecipeTest = new Recipe(1L, "Carbonara", "Carbon", false);
		Mockito.when(repo.findById(1L)).thenReturn(existingRecipe);
		Mockito.when(repo.saveAndFlush(outputRecipeTest)).thenReturn(outputRecipeTest);
		assertEquals(outputRecipeTest, service.update(1L, newRecipeTest));
		Mockito.verify(repo, Mockito.times(1)).findById(1L);
		Mockito.verify(repo, Mockito.times(1)).saveAndFlush(outputRecipeTest);
	}

	@Test
	public void deleteTest() {
		Recipe newRecipeTest = new Recipe(1L, "Spagbol", "not much", true);
		Mockito.when(repo.existsById(1L)).thenReturn(false);
		assertEquals(true, service.delete(1L));
		Mockito.verify(repo, Mockito.times(1)).deleteById(1L);
		Mockito.verify(repo, Mockito.times(1)).existsById(1L);
	}
}
