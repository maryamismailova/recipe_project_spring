package com.recipes.recipeproject.services;

import com.recipes.recipeproject.commands.IngredientCommand;
import com.recipes.recipeproject.domain.Ingredient;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);

    void deleteIngredientById(Long recipeId, Long ingredientId);

}
