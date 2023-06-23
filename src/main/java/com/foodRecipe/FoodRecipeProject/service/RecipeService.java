package com.foodRecipe.FoodRecipeProject.service;


import com.foodRecipe.FoodRecipeProject.exceptions.NoRecipesFoundWithGivenIngredientOrTitleException;
import com.foodRecipe.FoodRecipeProject.exceptions.NoRecipesFoundWithinTimeRangeException;
import com.foodRecipe.FoodRecipeProject.model.Ingredient;
import com.foodRecipe.FoodRecipeProject.model.Recipe;
import com.foodRecipe.FoodRecipeProject.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService implements IRecipeService{



    private RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository){
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Recipe> findAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes;

    }

    @Override
    public Recipe findById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }

    @Override
    public Recipe createNewRecipe(Recipe recipe) {

        for (Ingredient ingredient : recipe.getIngredients()) {
            ingredient.setRecipe(recipe);
        }
        return recipeRepository.save(recipe);

    }


    @Override
    public Recipe updateRecipe(Recipe recipe) {
        Recipe existingRecipe = findById(recipe.getId());
        if (existingRecipe == null) {
            throw new IllegalArgumentException("Recipe not found with ID: " + recipe.getId());
        }
        existingRecipe.setTitle(recipe.getTitle());
        existingRecipe.setDescription(recipe.getDescription());
        existingRecipe.setPreparationTime(recipe.getPreparationTime());
        existingRecipe.setInstructions(recipe.getInstructions());
        existingRecipe.setIngredients(recipe.getIngredients());

        return recipeRepository.save(existingRecipe);
    }

    @Override
    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);

    }

    @Override
    public Recipe findByIdWithIngredients(Long id) {
        return recipeRepository.findByIdWithIngredients(id);
    }



    @Override
    public List<Recipe> searchByTitleOrIngredients(String query) throws NoRecipesFoundWithGivenIngredientOrTitleException{
        List<Recipe> recipeList = recipeRepository.findByTitleContainingIgnoreCaseOrIngredientsNameContainingIgnoreCase(query, query);
        if(recipeList.isEmpty()){
            throw new NoRecipesFoundWithGivenIngredientOrTitleException("No recipes found with given recipe title or ingredient");
        }
        return recipeList;
    }

    @Override
    public List<Recipe> findByPreparationTimeRange(Integer minTime, Integer maxTime) throws NoRecipesFoundWithinTimeRangeException {
        List<Recipe> recipeList =  recipeRepository.findByPreparationTimeBetween(minTime, maxTime);
        if(recipeList.isEmpty()){
            throw new NoRecipesFoundWithinTimeRangeException("No recipes found within given time range");
        }
        return recipeList;
    }


}
