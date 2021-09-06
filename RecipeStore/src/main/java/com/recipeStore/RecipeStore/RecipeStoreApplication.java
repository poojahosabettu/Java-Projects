package com.recipeStore.RecipeStore;

import java.util.function.Function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class RecipeStoreApplication {

	public static void main(String[] args) {
		Function<String,String> function = (String s)->{
			return s;};
		SpringApplication.run(RecipeStoreApplication.class, args);
	}

}