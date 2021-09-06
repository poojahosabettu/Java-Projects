package com.recipeStore.RecipeStore.Services;

import com.recipeStore.RecipeStore.Domain.RecipeRequestObject;
import com.recipeStore.RecipeStore.Entities.Recipe;

import rcom.recipeStore.RecipeStore.Exception.ResourceNotFoundException;

public interface RecipeService {
	public Recipe saveRecipe(RecipeRequestObject object) ;
	public Recipe updateRecipe(RecipeRequestObject object, Long id) throws ResourceNotFoundException ;
	public String deleteRecipe(Long id) throws ResourceNotFoundException ;
}
