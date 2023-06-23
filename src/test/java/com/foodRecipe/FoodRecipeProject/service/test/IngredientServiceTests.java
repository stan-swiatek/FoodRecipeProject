package com.foodRecipe.FoodRecipeProject.service.test;

import com.foodRecipe.FoodRecipeProject.model.Ingredient;
import com.foodRecipe.FoodRecipeProject.repository.IngredientRepository;
import com.foodRecipe.FoodRecipeProject.service.IngredientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class IngredientServiceTests {


    @Mock
    private IngredientRepository ingredientRepository;

    @InjectMocks
    private IngredientService ingredientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindById() {
        Long ingredientId = 1L;
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientId);
        when(ingredientRepository.findById(ingredientId)).thenReturn(Optional.of(ingredient));
        Ingredient result = ingredientService.findById(ingredientId);
        assertEquals(ingredientId, result.getId());
        verify(ingredientRepository, times(1)).findById(ingredientId);
    }

    @Test
    void testDeleteIngredient() {

        Long ingredientId = 1L;
        ingredientService.deleteIngredient(ingredientId);
        verify(ingredientRepository, times(1)).deleteById(ingredientId);
    }

    @Test
    void testSaveIngredient() {

        Ingredient ingredient = new Ingredient();
        when(ingredientRepository.save(ingredient)).thenReturn(ingredient);
        Ingredient result = ingredientService.saveIngredient(ingredient);
        assertEquals(ingredient, result);
        verify(ingredientRepository, times(1)).save(ingredient);
    }


}
