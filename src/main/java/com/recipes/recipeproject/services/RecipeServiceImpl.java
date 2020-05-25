package com.recipes.recipeproject.services;

import com.recipes.recipeproject.domain.Recipe;
import com.recipes.recipeproject.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> findAll(){
        Iterable<Recipe> recipeList=recipeRepository.findAll();
        List<Recipe> recipes=new ArrayList<>();
        recipeList.forEach(recipe -> recipes.add(recipe));
        return recipes;
    }
}
