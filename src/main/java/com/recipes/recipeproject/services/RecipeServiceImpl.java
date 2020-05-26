package com.recipes.recipeproject.services;

import com.recipes.recipeproject.domain.Recipe;
import com.recipes.recipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> findAll(){
        log.debug("I am in the service");

        Iterable<Recipe> recipeList=recipeRepository.findAll();
        List<Recipe> recipes=new ArrayList<>();
        recipeList.forEach(recipe -> recipes.add(recipe));
        return recipes;
    }
}
