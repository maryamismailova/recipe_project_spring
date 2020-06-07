package com.recipes.recipeproject.services;

import com.recipes.recipeproject.commands.IngredientCommand;
import com.recipes.recipeproject.commands.RecipeCommand;
import com.recipes.recipeproject.converters.IngredientCommandToIngredient;
import com.recipes.recipeproject.converters.IngredientToIngredientCommand;
import com.recipes.recipeproject.domain.Ingredient;
import com.recipes.recipeproject.domain.Recipe;
import com.recipes.recipeproject.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {


    @Mock
    RecipeRepository recipeRepository;


    @InjectMocks
    IngredientServiceImpl ingredientService;


    @Test
    void deleteIngredientById() {
        //given
        Ingredient ingredientCommand = new Ingredient();
        ingredientCommand.setId(1L);
        Recipe recipeCommand = new Recipe();
        recipeCommand.setId(1L);
        recipeCommand.getIngredients().add(ingredientCommand);
        Optional<Recipe> recipeOptional = Optional.of(recipeCommand);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
//        //when
        ingredientService.deleteIngredientById(1L, 1L);

//        //then
        verify(recipeRepository, times(1)).findById(1L);
        verify(recipeRepository, times(1)).save(any());

    }
}