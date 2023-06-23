package com.foodRecipe.FoodRecipeProject.service;


import com.foodRecipe.FoodRecipeProject.exceptions.NoRecipesFoundWithGivenIngredientOrTitleException;
import com.foodRecipe.FoodRecipeProject.exceptions.NoRecipesFoundWithinTimeRangeException;
import com.foodRecipe.FoodRecipeProject.exceptions.NoRecipiesFoundWithGivenTitleAndPrepTimeException;
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
    public List<Recipe> findByTitleOrIngredientAndPrepTime(String query, Integer minTime, Integer maxTime) throws NoRecipesFoundWithinTimeRangeException, NoRecipesFoundWithGivenIngredientOrTitleException, NoRecipiesFoundWithGivenTitleAndPrepTimeException {

        List<Recipe> recipeList = recipeRepository.findByTitleAndPreparationTime((query != null ? query.toUpperCase() : ""), (minTime == null ? 0 : minTime), (maxTime == null ? Integer.MAX_VALUE : maxTime));


        if(recipeList.isEmpty()){
            if((minTime != null || maxTime != null) && query != null){
                throw new NoRecipiesFoundWithGivenTitleAndPrepTimeException("No recipes found with given recipe title, ingredient and within given preparation time");
            }
            if(minTime != null || maxTime != null) {
                throw new NoRecipesFoundWithinTimeRangeException("No recipes found within given time range");
            }
            if(query != null){
                throw new NoRecipesFoundWithGivenIngredientOrTitleException("No recipes found with given recipe title or ingredient");
            }
        }

        return recipeList;
    }




}
