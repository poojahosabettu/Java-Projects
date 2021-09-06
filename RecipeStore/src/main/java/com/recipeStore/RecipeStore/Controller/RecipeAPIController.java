package com.recipeStore.RecipeStore.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.recipeStore.RecipeStore.Domain.RecipeRequestObject;
import com.recipeStore.RecipeStore.Entities.Recipe;
import com.recipeStore.RecipeStore.Services.RecipeService;

@RestController
public class RecipeAPIController {

	@Autowired
	private RecipeService recipeService;

	@PostMapping("/recipe")
	public ResponseEntity<Recipe> createRecipe(@RequestBody RecipeRequestObject object) {
		try {
			return new ResponseEntity<Recipe>(recipeService.saveRecipe(object), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Recipe>(new Recipe(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/recipe/{id}")
	public ResponseEntity<Recipe> updateRecipe(@RequestBody RecipeRequestObject object,@PathVariable Long id) {
		try {
			return new ResponseEntity<Recipe>(recipeService.updateRecipe(object, id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Recipe>(new Recipe(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/recipe/{id}")
	public ResponseEntity<String> deleteRecipe(@PathVariable Long id) {
		try {
			return new ResponseEntity<String>(recipeService.deleteRecipe(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Could not delete the recipe", HttpStatus.BAD_REQUEST);
		}
	}
}