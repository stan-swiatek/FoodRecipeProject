package com.foodRecipe.FoodRecipeProject.service.test;


import com.foodRecipe.FoodRecipeProject.exceptions.NoRecipesFoundWithGivenIngredientOrTitleException;
import com.foodRecipe.FoodRecipeProject.exceptions.NoRecipesFoundWithinTimeRangeException;
import com.foodRecipe.FoodRecipeProject.exceptions.NoRecipiesFoundWithGivenTitleAndPrepTimeException;
import com.foodRecipe.FoodRecipeProject.model.Recipe;
import com.foodRecipe.FoodRecipeProject.repository.RecipeRepository;
import com.foodRecipe.FoodRecipeProject.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RecipeServiceTests {


    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeService recipeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAllRecipes() {

        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe());
        recipes.add(new Recipe());

        when(recipeRepository.findAll()).thenReturn(recipes);
        List<Recipe> result = recipeService.findAllRecipes();
        assertEquals(recipes, result);
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        // Arrange
        Long recipeId = 1L;
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(recipe));
        Recipe result = recipeService.findById(recipeId);
        assertEquals(recipeId, result.getId());
        verify(recipeRepository, times(1)).findById(recipeId);


    }

    @Test
    void testFindById_RecipeNotFound() {
        Long recipeId = 1L;
        when(recipeRepository.findById(recipeId)).thenReturn(Optional.empty());
        Recipe actualRecipe = recipeService.findById(recipeId);
        assertNull(actualRecipe);
        verify(recipeRepository, times(1)).findById(recipeId);
    }

    @Test
    void testCreateNewRecipe() {
        Recipe recipe = new Recipe();
        Recipe expectedRecipe = new Recipe();
        when(recipeRepository.save(recipe)).thenReturn(expectedRecipe);
        Recipe actualRecipe = recipeService.createNewRecipe(recipe);
        assertEquals(expectedRecipe, actualRecipe);
        verify(recipeRepository, times(1)).save(recipe);
    }


    @Test
    void testUpdateRecipe() {
        // Prepare test data
        Long recipeId = 1L;
        Recipe existingRecipe = new Recipe();
        existingRecipe.setId(recipeId);
        Recipe updatedRecipe = new Recipe();
        updatedRecipe.setId(recipeId);
        updatedRecipe.setTitle("Updated Title");
        updatedRecipe.setDescription("Updated Description");

        when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(existingRecipe));
        when(recipeRepository.save(existingRecipe)).thenReturn(updatedRecipe);

        Recipe actualRecipe = recipeService.updateRecipe(updatedRecipe);

        assertEquals(updatedRecipe, actualRecipe);
        verify(recipeRepository, times(1)).findById(recipeId);
        verify(recipeRepository, times(1)).save(existingRecipe);
    }

    @Test
    void testUpdateRecipe_RecipeNotFound() {

        Long recipeId = 1L;
        Recipe updatedRecipe = new Recipe();
        updatedRecipe.setId(recipeId);

        when(recipeRepository.findById(recipeId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> recipeService.updateRecipe(updatedRecipe));
        verify(recipeRepository, times(1)).findById(recipeId);
        verify(recipeRepository, never()).save(any());
    }

    @Test
    void testDeleteRecipe() {

        Long recipeId = 1L;
        recipeService.deleteRecipe(recipeId);
        verify(recipeRepository, times(1)).deleteById(recipeId);
    }

    @Test
    void testFindByIdWithIngredients() {

        Long recipeId = 1L;
        Recipe expectedRecipe = new Recipe();
        when(recipeRepository.findByIdWithIngredients(recipeId)).thenReturn(expectedRecipe);
        Recipe actualRecipe = recipeService.findByIdWithIngredients(recipeId);
        assertEquals(expectedRecipe, actualRecipe);
        verify(recipeRepository, times(1)).findByIdWithIngredients(recipeId);
    }



    @Test
    void testFindByTitleOrIngredientAndPrepTime_NoRecipesFoundWithTitleAndPrepTime() {

        String query = "chicken";
        Integer minTime = 20;
        Integer maxTime = 60;

        when(recipeRepository.findByTitleAndPreparationTime(anyString(), anyInt(), anyInt())).thenReturn(new ArrayList<>());
        assertThrows(NoRecipiesFoundWithGivenTitleAndPrepTimeException.class, () -> {
            recipeService.findByTitleOrIngredientAndPrepTime(query, minTime, maxTime);
        });
        verify(recipeRepository, times(1)).findByTitleAndPreparationTime(query.toUpperCase(), minTime, maxTime);
    }

    @Test
    void testFindByTitleOrIngredientAndPrepTime_NoRecipesFoundWithinTimeRange() {

        Integer minTime = 20;
        Integer maxTime = 60;

        when(recipeRepository.findByTitleAndPreparationTime(anyString(), anyInt(), anyInt())).thenReturn(new ArrayList<>());
        assertThrows(NoRecipesFoundWithinTimeRangeException.class, () -> {
            recipeService.findByTitleOrIngredientAndPrepTime(null, minTime, maxTime);
        });
        verify(recipeRepository, times(1)).findByTitleAndPreparationTime("", minTime, maxTime);
    }

    @Test
    void testFindByTitleOrIngredientAndPrepTime_NoRecipesFoundWithIngredientOrTitle() {

        String query = "chicken";

        when(recipeRepository.findByTitleAndPreparationTime(anyString(), anyInt(), anyInt())).thenReturn(new ArrayList<>());
        assertThrows(NoRecipesFoundWithGivenIngredientOrTitleException.class, () -> {
            recipeService.findByTitleOrIngredientAndPrepTime(query, null, null);
        });
        verify(recipeRepository, times(1)).findByTitleAndPreparationTime(query.toUpperCase(), 0, Integer.MAX_VALUE);
    }









}
