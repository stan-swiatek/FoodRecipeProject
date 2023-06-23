package com.foodRecipe.FoodRecipeProject.service;

import com.foodRecipe.FoodRecipeProject.model.Recipe;

import java.util.List;

public interface IRecipeService {

    public List<Recipe> findAllRecipes();

    Recipe findById(Long id);

    public Recipe createNewRecipe(Recipe recipe);

    public Recipe updateRecipe(Recipe recipe);

    public void deleteRecipe(Long id);


    Recipe findByIdWithIngredients(Long id);


//    List<Recipe> searchByTitleOrIngredients(String query) throws NoRecipesFoundWithGivenIngredientOrTitleException;
//
//    List<Recipe> findByPreparationTimeRange(Integer minTime, Integer maxTime) throws NoRecipesFoundWithinTimeRangeException;


    List<Recipe> findByTitleOrIngredientAndPrepTime(String query, Integer minTime, Integer maxTime);

}