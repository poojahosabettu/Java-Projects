package com.recipeStore.RecipeStore.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.recipeStore.RecipeStore.Entities.Ingredient;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
	
	@Query("select i from Ingredient i where i.name = :name")
	List<Ingredient> findByName(String name);
}
