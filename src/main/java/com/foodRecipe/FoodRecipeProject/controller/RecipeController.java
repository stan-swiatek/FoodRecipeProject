package com.foodRecipe.FoodRecipeProject.controller;
import com.foodRecipe.FoodRecipeProject.exceptions.NoRecipesFoundWithGivenIngredientOrTitleException;
import com.foodRecipe.FoodRecipeProject.exceptions.NoRecipesFoundWithinTimeRangeException;
import com.foodRecipe.FoodRecipeProject.model.Recipe;
import com.foodRecipe.FoodRecipeProject.service.IIngredientService;
import com.foodRecipe.FoodRecipeProject.service.IRecipeService;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final IRecipeService recipeService;
    private final IIngredientService ingredientService;

    public RecipeController(IRecipeService recipeService, IIngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }


    @GetMapping
    public List<Recipe> findAllRecipes() {
        return recipeService.findAllRecipes();
    }

    @GetMapping("/{id}")
    public Recipe getRecipeById(@PathVariable Long id) {
        return recipeService.findByIdWithIngredients(id);
    }


    @PostMapping("/create")
    public Recipe createNewRecipe(@RequestBody Recipe recipe) {
        return recipeService.createNewRecipe(recipe);
    }

    @PutMapping("/{id}")
    public Recipe updateRecipe( @RequestBody Recipe recipe) {
        return recipeService.updateRecipe(recipe);
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
    }


    @GetMapping("/search")
    public List<Recipe> searchRecipes(@RequestParam(value = "query", required = false) String query,
                                      @RequestParam(value = "minTime", required = false) Integer minTime,
                                      @RequestParam(value = "maxTime", required = false) Integer maxTime
                                ) throws NoRecipesFoundWithinTimeRangeException, NoRecipesFoundWithGivenIngredientOrTitleException {
        List<Recipe> searchResults = new ArrayList<>();

        if (query != null && !query.isEmpty()) {
            searchResults = recipeService.searchByTitleOrIngredients(query);

        } else if (minTime != null  || maxTime != null) {
            searchResults = recipeService.findByPreparationTimeRange(minTime, maxTime);
        }
        return searchResults;
    }

}




