package com.foodRecipe.FoodRecipeProject.service;

import com.foodRecipe.FoodRecipeProject.model.Ingredient;
import com.foodRecipe.FoodRecipeProject.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class IngredientService implements IIngredientService{


    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient findById(Long id) {
        return ingredientRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);

    }


    @Override
    public Ingredient saveIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

}
