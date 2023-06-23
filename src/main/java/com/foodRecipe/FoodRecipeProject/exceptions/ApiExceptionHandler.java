package com.foodRecipe.FoodRecipeProject.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ApiExceptionHandler {






    @ExceptionHandler(NoRecipesFoundWithinTimeRangeException.class)
    public ResponseEntity<ApiError> handleNoRecipesFoundWithinTimeRangeException(NoRecipesFoundWithinTimeRangeException ex) {
            ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "No recipes found within given time range");
            return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(NoRecipesFoundWithGivenIngredientOrTitleException.class)
    public ResponseEntity<ApiError>  handleNoRecipesFoundWithGivenIngredientOrTitleException(NoRecipesFoundWithGivenIngredientOrTitleException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "No recipes found with given recipe title or ingredient");
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(HttpClientErrorException.class)
    public String handleHttpClientErrorException(HttpClientErrorException ex, Model model) {
        System.out.println("Testing error1");
        model.addAttribute("message", ex.getMessage());
        return "recipes";
    }





}
