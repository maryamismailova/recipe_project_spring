package com.recipes.recipeproject.controllers;

import com.recipes.recipeproject.domain.Category;
import com.recipes.recipeproject.domain.Recipe;
import com.recipes.recipeproject.domain.UnitOfMeasure;
import com.recipes.recipeproject.repositories.CategoryRepository;
import com.recipes.recipeproject.repositories.UnitOfMeasureRepository;
import com.recipes.recipeproject.services.RecipeService;
import com.recipes.recipeproject.services.RecipeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController( RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model){
        List<Recipe> recipes= recipeService.findAll();
        model.addAttribute("recipes", recipes);

        return "index";
    }

}
