package com.foodRecipe.FoodRecipeProject.service.test;


import com.foodRecipe.FoodRecipeProject.exceptions.NoRecipesFoundWithGivenIngredientOrTitleException;
import com.foodRecipe.FoodRecipeProject.exceptions.NoRecipesFoundWithinTimeRangeException;
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
    void testSearchByTitleOrIngredients_NoRecipesFound() {

        String query = "chicken";
        when(recipeRepository.findByTitleContainingIgnoreCaseOrIngredientsNameContainingIgnoreCase(query, query))
                .thenReturn(new ArrayList<>());

        assertThrows(NoRecipesFoundWithGivenIngredientOrTitleException.class,
                () -> recipeService.searchByTitleOrIngredients(query));
    }

    @Test
    void testFindByPreparationTimeRange_NoRecipesFound() {

        Integer minTime = 10;
        Integer maxTime = 20;

        when(recipeRepository.findByPreparationTimeBetween(minTime, maxTime))
                .thenReturn(new ArrayList<>());
        assertThrows(NoRecipesFoundWithinTimeRangeException.class,
                () -> recipeService.findByPreparationTimeRange(minTime, maxTime));
    }


}
