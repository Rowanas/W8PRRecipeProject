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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.W8PR.domain.Recipe;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:testschema.sql", "classpath:testdata.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class RecipeControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	//throws in case I screw up
	public void createTest() throws Exception	{
		Recipe inputRecipeTest = new Recipe("Spagbol", "Spag, bol", true);
		String inputJSON = mapper.writeValueAsString(inputRecipeTest);
		
		Recipe outputRecipeTest = new Recipe(2L, "Spagbol", "Spag, bol", true);
		String outputJSON = mapper.writeValueAsString(outputRecipeTest);
		
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
		
		mvc.perform(get("/recipes/getAll")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(outputJSON));
	}
	
	@Test
	public void getByIdTest() throws Exception	{
		Recipe recipe = new Recipe(1L, "Eggs", "egg", true);
		String outputJSON = mapper.writeValueAsString(recipe);
		
		mvc.perform(get("/recipes/getById/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(outputJSON));
	}
	
	@Test
	public void updateTest() throws Exception	{
		Recipe inputRecipeTest = new Recipe("Spagbol", "Spag, bol", true);
		String inputJSON = mapper.writeValueAsString(inputRecipeTest);
		
		Recipe outputRecipeTest = new Recipe(1L, "Spagbol", "Spag, bol", true);
		String outputJSON = mapper.writeValueAsString(outputRecipeTest);
		
		mvc.perform(put("/recipes/update/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(inputJSON))
				.andExpect(status().isOk())
				.andExpect(content().json(outputJSON));
	}

	@Test
	public void deleteTest() throws Exception	{
		mvc.perform(delete("/recipes/delete/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}
}
