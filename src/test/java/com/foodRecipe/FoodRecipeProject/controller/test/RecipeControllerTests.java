package com.foodRecipe.FoodRecipeProject.controller.test;
import com.foodRecipe.FoodRecipeProject.controller.RecipeController;
import com.foodRecipe.FoodRecipeProject.exceptions.NoRecipesFoundWithGivenIngredientOrTitleException;
import com.foodRecipe.FoodRecipeProject.exceptions.NoRecipesFoundWithinTimeRangeException;
import com.foodRecipe.FoodRecipeProject.model.Recipe;
import com.foodRecipe.FoodRecipeProject.service.IIngredientService;
import com.foodRecipe.FoodRecipeProject.service.IRecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class RecipeControllerTests {

    private RecipeController recipeController;

    @Mock
    private IRecipeService recipeService;

    @Mock
    private IIngredientService ingredientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        recipeController = new RecipeController(recipeService, ingredientService);
    }

    @Test
    void testFindAllRecipes() {

        List<Recipe> expectedRecipes = new ArrayList<>();
        when(recipeService.findAllRecipes()).thenReturn(expectedRecipes);
        List<Recipe> actualRecipes = recipeController.findAllRecipes();
        assertEquals(expectedRecipes, actualRecipes);
        verify(recipeService, times(1)).findAllRecipes();
    }

    @Test
    void testGetRecipeById() {
        Long recipeId = 1L;
        Recipe expectedRecipe = new Recipe();
        when(recipeService.findByIdWithIngredients(recipeId)).thenReturn(expectedRecipe);

        Recipe actualRecipe = recipeController.getRecipeById(recipeId);

        assertEquals(expectedRecipe, actualRecipe);
        verify(recipeService, times(1)).findByIdWithIngredients(recipeId);
    }

    @Test
    void testCreateNewRecipe() {

        Recipe recipe = new Recipe();
        Recipe expectedRecipe = new Recipe();
        when(recipeService.createNewRecipe(recipe)).thenReturn(expectedRecipe);
        Recipe actualRecipe = recipeController.createNewRecipe(recipe);
        assertEquals(expectedRecipe, actualRecipe);
        verify(recipeService, times(1)).createNewRecipe(recipe);
    }

    @Test
    void testUpdateRecipe() {
        Recipe recipe = new Recipe();
        Recipe expectedRecipe = new Recipe();
        when(recipeService.updateRecipe(recipe)).thenReturn(expectedRecipe);
        Recipe actualRecipe = recipeController.updateRecipe(recipe);
        assertEquals(expectedRecipe, actualRecipe);
        verify(recipeService, times(1)).updateRecipe(recipe);
    }

    @Test
    void testDeleteRecipe() {
        Long recipeId = 1L;
        recipeController.deleteRecipe(recipeId);
        verify(recipeService, times(1)).deleteRecipe(recipeId);
    }

    @Test
    void testSearchRecipesByQuery() throws NoRecipesFoundWithinTimeRangeException, NoRecipesFoundWithGivenIngredientOrTitleException {

        String query = "chicken";
        List<Recipe> expectedSearchResults = new ArrayList<>();
        when(recipeService.searchByTitleOrIngredients(query)).thenReturn(expectedSearchResults);
        List<Recipe> actualSearchResults = recipeController.searchRecipes(query, null, null);
        assertEquals(expectedSearchResults, actualSearchResults);
        verify(recipeService, times(1)).searchByTitleOrIngredients(query);
        verify(recipeService, never()).findByPreparationTimeRange(anyInt(), anyInt());
    }

    @Test
    void testSearchRecipesByTimeRange() throws NoRecipesFoundWithinTimeRangeException, NoRecipesFoundWithGivenIngredientOrTitleException {

        Integer minTime = 10;
        Integer maxTime = 30;
        List<Recipe> expectedSearchResults = new ArrayList<>();
        when(recipeService.findByPreparationTimeRange(minTime, maxTime)).thenReturn(expectedSearchResults);
        List<Recipe> actualSearchResults = recipeController.searchRecipes(null, minTime, maxTime);
        assertEquals(expectedSearchResults, actualSearchResults);
        verify(recipeService, times(1)).findByPreparationTimeRange(minTime, maxTime);
        verify(recipeService, never()).searchByTitleOrIngredients(anyString());
    }







}
