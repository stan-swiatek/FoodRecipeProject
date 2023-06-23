package com.foodRecipe.FoodRecipeProject.controller.test;

import com.foodRecipe.FoodRecipeProject.controller.RecipeGUIController;
import com.foodRecipe.FoodRecipeProject.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RecipeGUIControllerTests {
    @Mock
    private RestTemplate restTemplate;

    @Mock
    private Model model;


    @InjectMocks
    private RecipeGUIController recipeGUIController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAllRecipes_Success() {

        Recipe[] recipes = {new Recipe(), new Recipe()};
        ResponseEntity<Recipe[]> responseEntity = new ResponseEntity<>(recipes, HttpStatus.OK);
        when(restTemplate.getForEntity(anyString(), eq(Recipe[].class))).thenReturn(responseEntity);
        String result = recipeGUIController.findAllRecipes(model);
        assertEquals("recipes", result);
        verify(restTemplate, times(1)).getForEntity(anyString(), eq(Recipe[].class));
        verify(model, times(1)).addAttribute(eq("recipes"), eq(recipes));
    }

    @Test
    void testFindAllRecipes_Error() {

        ResponseEntity<Recipe[]> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        when(restTemplate.getForEntity(anyString(), eq(Recipe[].class))).thenReturn(responseEntity);
        String result = recipeGUIController.findAllRecipes(model);
        assertEquals("error-page", result);
        verify(restTemplate, times(1)).getForEntity(anyString(), eq(Recipe[].class));
        verify(model, times(0)).addAttribute(anyString(), any());
    }


    @Test
    void testShowCreateForm() {

        String result = recipeGUIController.showCreateForm(model);
        assertEquals("recipe-form", result);
        verify(model, times(1)).addAttribute(eq("recipe"), any(Recipe.class));
    }

    @Test
    void testCreateNewRecipe_Success() {
        // Arrange
        Recipe recipe = new Recipe();
        ResponseEntity<Recipe> responseEntity = new ResponseEntity<>(recipe, HttpStatus.OK);
        when(restTemplate.postForEntity(anyString(), eq(recipe), eq(Recipe.class))).thenReturn(responseEntity);
        String result = recipeGUIController.createNewRecipe(recipe);
        assertEquals("redirect:/recipes/" + recipe.getId(), result);
        verify(restTemplate, times(1)).postForEntity(anyString(), eq(recipe), eq(Recipe.class));
    }

    @Test
    void testCreateNewRecipe_Error() {

        Recipe recipe = new Recipe();
        ResponseEntity<Recipe> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        when(restTemplate.postForEntity(anyString(), eq(recipe), eq(Recipe.class))).thenReturn(responseEntity);
        String result = recipeGUIController.createNewRecipe(recipe);
        assertEquals("error-page", result);
        verify(restTemplate, times(1)).postForEntity(anyString(), eq(recipe), eq(Recipe.class));
    }

    @Test
    void testAddIngredientToNewRecipe() {

        Recipe recipe = new Recipe();
        String newIngredient = "New Ingredient";
        String result = recipeGUIController.addIngredientToNewRecipe(recipe, newIngredient);
        assertEquals("recipe-form", result);
        assertEquals(1, recipe.getIngredients().size());
        assertEquals(newIngredient, recipe.getIngredients().get(0).getName());
    }



    @Test
    void testShowEditForm() {

        Long id = 1L;
        Recipe recipe = new Recipe();
        when(restTemplate.getForObject(anyString(), eq(Recipe.class))).thenReturn(recipe);
        String result = recipeGUIController.showEditForm(id, model);
        assertEquals("edit", result);
        verify(restTemplate, times(1)).getForObject(anyString(), eq(Recipe.class));
        verify(model, times(1)).addAttribute(eq("recipe"), eq(recipe));
    }

    @Test
    void testUpdateRecipe() {

        Long id = 1L;
        Recipe updatedRecipe = new Recipe();
        String result = recipeGUIController.updateRecipe(id, updatedRecipe);
        assertEquals("redirect:/recipes/" + id, result);
        verify(restTemplate, times(1)).put(anyString(), eq(updatedRecipe));
    }

    @Test
    void testAddIngredient() {

        Recipe updatedRecipe = new Recipe();
        String newIngredient = "New Ingredient";
        String result = recipeGUIController.addIngredient(updatedRecipe, newIngredient);
        assertEquals("edit", result);
        assertEquals(1, updatedRecipe.getIngredients().size());
        assertEquals(newIngredient, updatedRecipe.getIngredients().get(0).getName());
    }



    @Test
    void testDeleteRecipe() {

        Long id = 1L;
        String result = recipeGUIController.deleteRecipe(id);
        assertEquals("redirect:/recipes", result);
        verify(restTemplate, times(1)).delete(anyString());
    }





}
