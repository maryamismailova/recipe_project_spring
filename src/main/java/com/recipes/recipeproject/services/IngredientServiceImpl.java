package com.recipes.recipeproject.services;

import com.recipes.recipeproject.commands.IngredientCommand;
import com.recipes.recipeproject.converters.IngredientCommandToIngredient;
import com.recipes.recipeproject.converters.IngredientToIngredientCommand;
import com.recipes.recipeproject.domain.Ingredient;
import com.recipes.recipeproject.domain.Recipe;
import com.recipes.recipeproject.repositories.RecipeRepository;
import com.recipes.recipeproject.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final  UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final RecipeRepository recipeRepository;

    public IngredientServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, IngredientToIngredientCommand ingredientToIngredientCommand, IngredientCommandToIngredient ingredientCommandToIngredient, RecipeRepository recipeRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(!recipeOptional.isPresent()){
            log.error("recipe if not found. Id: "+recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients()
                .stream().filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        if(!ingredientCommandOptional.isPresent()){
            log.error("Ingredient id not found, Id: "+ingredientId);
        }

        return ingredientCommandOptional.get();
    }

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        //FIND THE RECIPE
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        //CHECK IF RECIPE EXISTS
       if(!recipeOptional.isPresent()){
           log.error("Recipe not found. Id: "+command.getRecipeId());
           return new IngredientCommand();
       }else{
           Recipe recipe = recipeOptional.get();

           //CHECK IF INGREDIENT EXISTS(UPDATE) OR NOT(NEW)
           Optional<Ingredient> ingredientOptional = recipe
                   .getIngredients()
                   .stream()
                   .filter(ingredient -> ingredient.getId().equals(command.getId()))
                   .findFirst();
            //->UPDATE
           if(ingredientOptional.isPresent()){
               Ingredient ingredientFound = ingredientOptional.get();
               ingredientFound.setDescription(command.getDescription());
               ingredientFound.setAmount(command.getAmount());
               ingredientFound.setUof(unitOfMeasureRepository
                    .findById(command.getUof().getId())
                    .orElseThrow( ()->new RuntimeException("UOM NOT FOUND") ));
           }else{
               //->NEW INGREDIENT
               Ingredient ingredient = ingredientCommandToIngredient.convert(command);
               ingredient.setRecipe(recipe);
               recipe.addIngredient(ingredient);
           }
           //UPDATE recipe
           Recipe savedRecipe = recipeRepository.save(recipe);

           //FIND THE ID OF SAVED/UPDATED INGREDIENT
           //get the saved ingredient
           Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
                   .filter(recipeIngredient -> recipeIngredient.getId().equals(command.getId()))
                   .findFirst();
            //if the ingredient had no id(NEW INGREDIENT)
           //find the that ingredient's created entity in the list of ingredients
           if(!savedIngredientOptional.isPresent()){
               savedIngredientOptional = savedRecipe.getIngredients().stream()
                       .filter(ingredient -> ingredient.getDescription().equals(command.getDescription()))
                       .filter(ingredient -> ingredient.getAmount().equals(command.getAmount()))
                       .filter(ingredient -> ingredient.getUof().getId().equals(command.getUof().getId()))
                       .findFirst();
           }
           //and return the correct ingredient (with id)
           return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
       }
    }

    @Override
    public void deleteIngredientById(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(recipeOptional.isPresent()){
            Recipe recipe = recipeOptional.get();
            Optional<Ingredient> ingredientOptional=recipe.getIngredients().stream().filter(ingredient -> ingredient.getId().equals(ingredientId)).findFirst();
            if(ingredientOptional.isPresent()){
                ingredientOptional.get().setRecipe(null);
                recipe.getIngredients().removeIf(ingredient -> ingredient.getId().equals(ingredientId));
                recipeRepository.save(recipe);
            }else{
                System.out.println("NOT FOUND!");
            }
        }
    }
}
