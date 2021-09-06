package com.recipeStore.RecipeStore.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.recipeStore.RecipeStore.Entities.Recipe;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
	
	

}