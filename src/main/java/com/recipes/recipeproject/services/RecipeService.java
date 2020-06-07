package com.recipes.recipeproject.services;

import com.recipes.recipeproject.commands.RecipeCommand;
import com.recipes.recipeproject.controllers.RecipeController;
import com.recipes.recipeproject.domain.Recipe;

import java.util.List;

public interface RecipeService {
    public List<Recipe> findAll();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    RecipeCommand findCommandById(Long id);

    void deleteById(Long id);
}
