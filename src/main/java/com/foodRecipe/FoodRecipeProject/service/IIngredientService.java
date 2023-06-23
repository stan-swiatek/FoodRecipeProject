package com.foodRecipe.FoodRecipeProject.service;

import com.foodRecipe.FoodRecipeProject.model.Ingredient;

public interface IIngredientService {

    Ingredient findById(Long id);
    void deleteIngredient(Long id);

    Ingredient saveIngredient(Ingredient ingredient);
}
