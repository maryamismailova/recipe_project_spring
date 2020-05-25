package com.recipes.recipeproject.services;

import com.recipes.recipeproject.domain.Recipe;

import java.util.List;

public interface RecipeService {
    public List<Recipe> findAll();
}
