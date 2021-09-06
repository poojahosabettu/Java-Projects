package com.recipeStore.RecipeStore.ServicesImpl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipeStore.RecipeStore.Domain.RecipeRequestObject;
import com.recipeStore.RecipeStore.Entities.Ingredient;
import com.recipeStore.RecipeStore.Entities.Recipe;
import com.recipeStore.RecipeStore.Repository.IngredientRepository;
import com.recipeStore.RecipeStore.Repository.RecipeRepository;
import com.recipeStore.RecipeStore.Services.RecipeService;

import rcom.recipeStore.RecipeStore.Exception.ResourceNotFoundException;

@Service
public class RecipeDataServiceImpl implements RecipeService {

	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private IngredientRepository ingredientRepository;

	@Autowired
	private ModelMapper modelMapper;

	public Recipe saveRecipe(RecipeRequestObject object) {
		Recipe recipe = modelMapper.map(object, Recipe.class);
		recipe.setIngredients(new HashSet<Ingredient>());
		for (String ingredient : object.getIngredients()) {
			List<Ingredient> ingredients = ingredientRepository.findByName(ingredient);
			if (ingredients.isEmpty()) {
				Ingredient temp = new Ingredient();
				temp.setName(ingredient);
				recipe.getIngredients().add(temp);
			} else
				recipe.getIngredients().add(ingredients.get(0));
		}
		return recipeRepository.save(recipe);
	}

	@Override
	public Recipe updateRecipe(RecipeRequestObject object, Long id) throws ResourceNotFoundException {
		Recipe tempRecipe = modelMapper.map(object, Recipe.class);
		return recipeRepository.findById(id).map((recipe) -> {
			recipe.setDish(null != tempRecipe.getDish() ? tempRecipe.getDish() : recipe.getDish());
			recipe.setInstruction(
					null != tempRecipe.getInstruction() ? tempRecipe.getInstruction() : recipe.getInstruction());
			recipe.setServes(null != tempRecipe.getServes() ? tempRecipe.getServes() : recipe.getServes());

			if (null != object.getIngredients() && !object.getIngredients().isEmpty()) {
				for (String ingredient : object.getIngredients()) {
					List<Ingredient> ingredients = ingredientRepository.findByName(ingredient);
					if (ingredients.isEmpty()) {
						Ingredient temp = new Ingredient();
						temp.setName(ingredient);
						recipe.getIngredients().add(temp);
					} else
						recipe.getIngredients().add(ingredients.get(0));
				}
			}
			return recipeRepository.save(recipe);
		}).orElseThrow(() -> new ResourceNotFoundException("Recipe not found  :: " + id));
	}

	@Override
	public String deleteRecipe(Long id) throws ResourceNotFoundException {
		return recipeRepository.findById(id).map((recipe) -> {
			Iterator iterable = recipe.getIngredients().iterator();
			while(iterable.hasNext()) {
				iterable.next();
				iterable.remove();
			}
			recipeRepository.delete(recipe);
			return "Deleted recipe successfully";

		}).orElseThrow(() -> new ResourceNotFoundException("Recipe not found  :: " + id));
	}
}
