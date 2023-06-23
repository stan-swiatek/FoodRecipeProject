package com.foodRecipe.FoodRecipeProject.exceptions;

import org.springframework.http.HttpStatus;

public class NoRecipesFoundWithGivenIngredientOrTitleException extends ApiException {
    public NoRecipesFoundWithGivenIngredientOrTitleException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
