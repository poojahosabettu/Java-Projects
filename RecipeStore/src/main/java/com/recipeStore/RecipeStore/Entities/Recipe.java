package com.recipeStore.RecipeStore.Entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Recipe {
	
	@Id
	@Column(name="Recipe_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REC_SEQ")
    @SequenceGenerator(sequenceName = "recipe_seq", allocationSize = 1, name = "REC_SEQ")
    private Long recipeId;
	
	@Column(name="Dish",nullable = false)
	private String dish;

	@Column(name="CreatedTs")
	private LocalDateTime createdDateTime;
	
	@Column(name="Vegetarian")
	private Boolean vegetarian;
	
	@Column(name="Serves")
	private Integer serves;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "RECIPE_INGREDIENT", joinColumns = { @JoinColumn(name = "RECIPE_ID") }, inverseJoinColumns = { @JoinColumn(name = "INGREDIENT_ID") })
	private Set<Ingredient> ingredients = new HashSet<Ingredient>();
	
	@Column(name="Instruction")
	@Lob
	private String instruction;


	public Long getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Long recipe_id) {
		this.recipeId = recipe_id;
	}
	
	public String getDish() {
		return dish;
	}

	public void setDish(String dish) {
		this.dish = dish;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Boolean getVegetarian() {
		return vegetarian;
	}

	public void setVegetarian(Boolean vegetarian) {
		this.vegetarian = vegetarian;
	}

	public Integer getServes() {
		return serves;
	}

	public void setServes(Integer serves) {
		this.serves = serves;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	
	
	
}
