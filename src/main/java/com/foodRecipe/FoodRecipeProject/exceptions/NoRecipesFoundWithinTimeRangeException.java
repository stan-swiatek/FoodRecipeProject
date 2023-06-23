package com.foodRecipe.FoodRecipeProject.exceptions;

import org.springframework.http.HttpStatus;

public class NoRecipesFoundWithinTimeRangeException extends ApiException{

    public NoRecipesFoundWithinTimeRangeException(String message){
        super(HttpStatus.NOT_FOUND, message);
    }

}
