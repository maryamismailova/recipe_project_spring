package com.recipes.recipeproject.repositories;

import com.recipes.recipeproject.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    
}
