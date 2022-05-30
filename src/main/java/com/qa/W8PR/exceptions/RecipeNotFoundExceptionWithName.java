package com.qa.W8PR.exceptions;

import java.util.NoSuchElementException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RecipeNotFoundExceptionWithName extends NoSuchElementException {
// created as proof of learning, and allows me to pass recipeName as an argument	
	public RecipeNotFoundExceptionWithName(String recipeName)	{
		super(recipeName+" does not exist, but it sounds tasty");
				
	}
}
