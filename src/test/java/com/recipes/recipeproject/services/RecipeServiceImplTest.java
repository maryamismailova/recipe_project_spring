package com.recipes.recipeproject.services;

import com.recipes.recipeproject.domain.Recipe;
import com.recipes.recipeproject.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService=new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void findAll() {
        Recipe recipe = new Recipe();
        List<Recipe> recipeData = new ArrayList<>();
        recipeData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipeData);

        List<Recipe> recipeSet = recipeService.findAll();

        assertEquals(recipeData, recipeSet);
        verify(recipeRepository, times(1)).findAll();
    }
}