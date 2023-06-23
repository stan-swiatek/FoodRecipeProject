package com.foodRecipe.FoodRecipeProject.data;


import com.foodRecipe.FoodRecipeProject.model.Ingredient;
import com.foodRecipe.FoodRecipeProject.model.Recipe;
import com.foodRecipe.FoodRecipeProject.repository.IngredientRepository;
import com.foodRecipe.FoodRecipeProject.repository.RecipeRepository;
import com.foodRecipe.FoodRecipeProject.service.IIngredientService;
import com.foodRecipe.FoodRecipeProject.service.IRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataImport implements ApplicationRunner {

    @Autowired
    private IRecipeService recipeService;

    @Autowired
    private IIngredientService ingredientService;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;



    @Override
    public void run(ApplicationArguments args) throws Exception {

        Ingredient ingredient = new Ingredient(1L, "sweet peppers", new Recipe());
        Ingredient ingredient2 = new Ingredient(2L, "prosciutto", new Recipe());
        Ingredient ingredient3 = new Ingredient(3L, "spreadable cheese", new Recipe());
        Ingredient ingredient4 = new Ingredient(4L, "chicken breast", new Recipe());
        Ingredient ingredient5 = new Ingredient(5L, "mozzarella cheese", new Recipe());
        Ingredient ingredient6 = new Ingredient(6L, "egg", new Recipe());
        Ingredient ingredient7 = new Ingredient(7L, "Hot sauce", new Recipe());
        Ingredient ingredient8 = new Ingredient(8L, "Cooking spray", new Recipe());
        Ingredient ingredient9 = new Ingredient(9L, "pizza dough", new Recipe());
        Ingredient ingredient10 = new Ingredient(10L, "Roma tomatoes", new Recipe());
        Ingredient ingredient11 = new Ingredient(11L, "basil ", new Recipe());
        Ingredient ingredient12 = new Ingredient(12L, "Dried oregano", new Recipe());
        Ingredient ingredient13 = new Ingredient(13L, "Olive oil", new Recipe());
        Ingredient ingredient14 = new Ingredient(14L, "peanut butter", new Recipe());
        Ingredient ingredient15 = new Ingredient(15L, "sugar", new Recipe());

        Recipe recipe1 = new Recipe(1L, "Air Fryer Stuffed Mini Peppers ", "These quick air fried stuffed mini pepper appetizers are ready in less than 15 min.", "Preheat the air fryer to 350 degrees F (175 degrees C). Cut each pepper in half lengthwise, and remove seeds and stem.\n" +
                "\n" +
                "Slice prosciutto into pieces the size of pepper halves, and lay one piece in each pepper half. Spread 1 tablespoon cheese into each pepper half. If prosciutto hangs over pepper sides, fold it upward to cover cheese.\n" +
                "\n" +
                "Air fry until cheese is very warm and soft, about 5 minutes. Enjoy!", 15, Arrays.asList(ingredient, ingredient2, ingredient3));


        Recipe recipe2 = new Recipe(2L, "Air-fried Chicken Nuggets", "3-Ingredient Chicken Nuggets Are the Best Thing to Happen to the Air Fryer", "Preheat the air fryer to 400 degrees F. Drain any liquid from the canned chicken and add the chicken to a bowl. Shred finely using forks.\n" +
                "Add the cheese, egg, and hot sauce (if desired) to the bowl and stir until thoroughly combined.\n" +
                "Form 6-7 chicken patties from the mixture. Spray the nuggets and the air fryer basket with cooking spray. Cook for 15-20 minutes, and allow to cool before serving.", 25, Arrays.asList(ingredient4, ingredient5, ingredient6, ingredient7, ingredient8));

        Recipe recipe3 = new Recipe(3L, " Pizza Appetizer", "The best thing about this recipe is that you can easily make it your own by adding more fun\n" +
                "toppings", "Preheat the oven to 400°F with a rack in the center position. Lightly oil a 13- by 18-inch\n" +
                "rimmed baking sheet.\n" +
                "Thinly slice tomatoes and place them on a paper towel-lined plate. Pat the tops of to\n" +
                "tomatoes dry.\n" +
                "Gently stretch and press pizza dough to each corner of the rimmed baking sheet. Drizzle with oil, then season all over with salt, pepper, and oregano (or talian seasoning).\n" +
                "Cover the pizza dough with sliced tomatoes (they should touch). Bake pizza until the\n" +
                "crust is golden brown and the tomatoes look roasted, 22 to 25 minutes.\n" +
                "Let cool slightly, then transfer to a cutting board and cut into small squares. Sprinkle with a pinch of salt and garnish with fresh basil.", 25, Arrays.asList(ingredient9, ingredient10, ingredient11, ingredient12, ingredient13));


        Recipe recipe4 = new Recipe(4L, "Peanut Butter Cookies", "Whip up these scrumptious 3-Ingredient Peanut Butter Cookies in a snap!", "Preheat oven to 350ºF (180ºC).\n" +
                "In a large bowl, mix together the peanut butter, sugar, and egg.\n" +
                "Scoop out a spoonful of dough and roll it into a ball. Place the cookie balls onto a nonstick baking sheet.\n" +
                "For extra decoration and to make them cook more evenly, flatten the cookie balls by pressing a fork down on top of them, then press it down again at a 90º angle to make a criss-cross pattern.\n" +
                "Bake for 8-10 minutes or until the bottom of the cookies are golden brown.\n" +
                "Remove from baking sheet and cool.", 15, Arrays.asList(ingredient6,ingredient14, ingredient15));



        recipeService.createNewRecipe(recipe1);
        recipeService.createNewRecipe(recipe2);
        recipeService.createNewRecipe(recipe3);
        recipeService.createNewRecipe(recipe4);




    }



}
