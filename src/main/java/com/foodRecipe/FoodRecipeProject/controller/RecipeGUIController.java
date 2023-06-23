package com.foodRecipe.FoodRecipeProject.controller;
import com.foodRecipe.FoodRecipeProject.model.Ingredient;
import com.foodRecipe.FoodRecipeProject.model.Recipe;
import com.foodRecipe.FoodRecipeProject.service.IRecipeService;
import com.foodRecipe.FoodRecipeProject.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Controller
@RequestMapping("/recipes")
public class RecipeGUIController {

    private final RestTemplate restTemplate;
    private final String apiUrl;
    private final IRecipeService recipeService;


    public RecipeGUIController(RestTemplate restTemplate,
                               @Value("${api.url}") String apiUrl, RecipeService recipeService) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
        this.recipeService = recipeService;
    }


    @GetMapping
    public String findAllRecipes(Model model) {
        ResponseEntity<Recipe[]> response = restTemplate.getForEntity(apiUrl + "/api/recipes", Recipe[].class);
        if (response.getStatusCode() == HttpStatus.OK) {
            Recipe[] recipes = response.getBody();
            model.addAttribute("recipes", recipes);
            return "recipes";
        } else {
            return "error-page";
        }
    }

    @GetMapping("/{id}")
    public String getRecipeById(@PathVariable Long id, Model model) {
        Recipe recipe = restTemplate.getForObject(apiUrl + "/api/recipes/" + id, Recipe.class);
        model.addAttribute("recipe", recipe);
        return "recipe-details";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "recipe-form";
    }


    //  Save Button for create recipe
    @RequestMapping(value = "/create", method = RequestMethod.POST, params = "saveButton")
    public String createNewRecipe(@Valid @ModelAttribute("recipe") Recipe recipe) {
        ResponseEntity<Recipe> response = restTemplate.postForEntity(apiUrl + "/api/recipes/create", recipe, Recipe.class);
        if (response.getStatusCode() == HttpStatus.OK) {

            Recipe createdRecipe = response.getBody();
            Long recipeId = createdRecipe.getId();
            return "redirect:/recipes/" + recipeId;
        } else {
            return "error-page";
        }
    }

    //  add ingredient button for create recipe
    @RequestMapping(value = "/create", method = RequestMethod.POST, params = "addIngredient")
    public String addIngredientToNewRecipe(@Valid @ModelAttribute("recipe") Recipe recipe,
                                           @RequestParam(name = "newIngredient", required = false) String newIngredient) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(newIngredient);
        ingredient.setRecipe(recipe);
        recipe.getIngredients().add(ingredient);
        return "recipe-form";
    }


    //  Remove Ingredient Button for create recipe
    @RequestMapping(value = "/create", method = RequestMethod.POST, params = "removeIngredient")
    public String removeIngredientFromNewRecipe(@Valid @ModelAttribute("recipe") Recipe recipe,
                                                @RequestParam(name = "removeIngredient", required = false) int removeIngredient) {
        recipe.getIngredients().remove(removeIngredient);
        return "recipe-form";
    }


    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Recipe recipe = restTemplate.getForObject(apiUrl + "/api/recipes/" + id, Recipe.class);
        model.addAttribute("recipe", recipe);
        return "edit";
    }


    //  Update Recipe Button in edit form
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST, params = "updateRecipe")
    public String updateRecipe(@PathVariable Long id,
                               @Valid @ModelAttribute("recipe") Recipe updatedRecipe) {
        restTemplate.put(apiUrl + "/api/recipes/" + id, updatedRecipe);

        return "redirect:/recipes/" + id;
    }

    //  Add Ingredient Button in edit form
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST, params = "addIngredient")
    public String addIngredient(@Valid @ModelAttribute("recipe") Recipe updatedRecipe,
                                @RequestParam(name = "newIngredient", required = false) String newIngredient) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(newIngredient);
        ingredient.setRecipe(updatedRecipe);
        updatedRecipe.getIngredients().add(ingredient);
        return "edit";
    }

    //  Remove Ingredient Button in edit form
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST, params = "removeIngredient")
    public String removeIngredient(@Valid @ModelAttribute("recipe") Recipe updatedRecipe,
                                   @RequestParam(name = "removeIngredient", required = false) int removeIngredient) {
        updatedRecipe.getIngredients().remove(removeIngredient);
        return "edit";
    }


    // Deleting recipe
    @PostMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable Long id) {
        restTemplate.delete(apiUrl + "/api/recipes/" + id);
        return "redirect:/recipes";
    }


    @GetMapping("/search")
    public String searchRecipes(@RequestParam(value = "query", required = false) String query,
                                @RequestParam(value = "minTime", required = false) Integer minTime,
                                @RequestParam(value = "maxTime", required = false) Integer maxTime,
                                Model model) throws HttpClientErrorException {
        ResponseEntity<?> response = restTemplate.getForEntity(apiUrl + "/api/recipes/search?query={query}&minTime={minTime}&maxTime={maxTime}",
                Recipe[].class, query, minTime, maxTime);

        if (response.getStatusCode() == HttpStatus.OK) {
            Object recipes = response.getBody();
            model.addAttribute("recipes", recipes);
        }
        return "recipes";
    }

}







































