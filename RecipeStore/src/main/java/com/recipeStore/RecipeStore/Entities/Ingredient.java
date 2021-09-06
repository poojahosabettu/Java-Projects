package com.recipeStore.RecipeStore.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Ingredient {
	
	@Id
	@Column(name="ingredient_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ING_SEQ")
    @SequenceGenerator(sequenceName = "ingredient_seq", allocationSize = 1, name = "ING_SEQ")
    private Long id;
	
	@Column(name="ingredient_name",nullable = false)
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
