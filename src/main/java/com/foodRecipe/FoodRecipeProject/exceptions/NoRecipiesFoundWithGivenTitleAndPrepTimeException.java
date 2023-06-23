package com.foodRecipe.FoodRecipeProject.exceptions;

import org.springframework.http.HttpStatus;

public class NoRecipiesFoundWithGivenTitleAndPrepTimeException extends ApiException {

    public NoRecipiesFoundWithGivenTitleAndPrepTimeException(String message) {

        super(HttpStatus.NOT_FOUND, message);
    }
}
