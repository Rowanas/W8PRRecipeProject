package com.qa.W8PR.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.W8PR.domain.Recipe;

@Repository // everything we need is in JpaRepository for now.
public interface RecipeRepo extends JpaRepository<Recipe, Long> {
	//must use objects within, so wrapper of long is Long

	Optional<Recipe> findRecipeByRecipeName(String recipeName);
	
	//finds only recipes that are diet friendly. Done as manual query for proof of learning
	@Query(value = "SELECT * FROM recipe WHERE diet_friendly = ?1", nativeQuery = true)
	List<Recipe> findRecipeByDietFriendly(boolean dietFriendly);
}
