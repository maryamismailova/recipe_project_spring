package com.recipes.recipeproject.controllers;

import com.recipes.recipeproject.domain.Category;
import com.recipes.recipeproject.domain.Ingredient;
import com.recipes.recipeproject.domain.Recipe;
import com.recipes.recipeproject.domain.UnitOfMeasure;
import com.recipes.recipeproject.repositories.CategoryRepository;
import com.recipes.recipeproject.repositories.UnitOfMeasureRepository;
import com.recipes.recipeproject.services.RecipeService;
import com.recipes.recipeproject.services.RecipeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController( RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model){
        log.debug("Getting index page");

        List<Recipe> recipes= recipeService.findAll();
        model.addAttribute("recipes", recipes);

        return "index";
    }

}
