package com.qa.W8PR.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.W8PR.domain.Recipe;
import com.qa.W8PR.services.RecipeService;

@WebMvcTest
public class RecipeControllerUnitTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;
	
	@MockBean
	private RecipeService service;
	
	@Test
	//throws in case I screw up
	public void createTest() throws Exception	{
		Recipe inputRecipeTest = new Recipe("Spagbol", "Spag, bol", true);
		String inputJSON = mapper.writeValueAsString(inputRecipeTest);
		
		Recipe outputRecipeTest = new Recipe(1L, "Spagbol", "Spag, bol", true);
		String outputJSON = mapper.writeValueAsString(outputRecipeTest);
		
		Mockito.when(service.create(inputRecipeTest)).thenReturn(outputRecipeTest);
		
		mvc.perform(post("/recipes/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(inputJSON))
				.andExpect(status().isCreated())
				.andExpect(content().json(outputJSON));
}
	
	@Test
	public void getallTest() throws Exception	{
		Recipe recipe = new Recipe(1L, "Eggs", "egg", true);
		List<Recipe> outputRecipeTest = new ArrayList <>();
		outputRecipeTest.add(recipe);
		String outputJSON = mapper.writeValueAsString(outputRecipeTest);
		
		Mockito.when(service.getAll()).thenReturn(outputRecipeTest);
		
		mvc.perform(get("/recipes/getAll")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(outputJSON));
	}

	
	@Test
	public void updateTest() throws Exception	{
		Recipe inputRecipeTest = new Recipe("Spagbol", "Spag, bol", true);
		String inputJSON = mapper.writeValueAsString(inputRecipeTest);
		
		Mockito.when(service.update(1L, inputRecipeTest)).thenReturn(inputRecipeTest);
		
		mvc.perform(put("/recipes/update/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(inputJSON))
				.andExpect(status().isOk())
				.andExpect(content().json(inputJSON));
	}
	
	@Test
	public void deleteTest() throws Exception	{
		
		Mockito.when(service.delete(1L)).thenReturn(true);
		
		mvc.perform(delete("/recipes/delete/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}
	
	//these tests are in place for when I expand upon this
	//project later, to go with functions also
	//commented out in controller
	@Disabled
	@Test
	public void getRecipeByNameTest()	{
		
	}
	
	@Disabled
	@Test
	public void getDietFriendlyTest() throws Exception	{
		
	}
	
	@Disabled
	@Test
	public void getByIdTest()	{
		
	}
}
