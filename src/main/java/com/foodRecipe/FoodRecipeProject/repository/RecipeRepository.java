package com.foodRecipe.FoodRecipeProject.repository;
import org.springframework.stereotype.Repository;
import com.foodRecipe.FoodRecipeProject.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {



    @Query("SELECT r FROM Recipe r LEFT JOIN FETCH r.ingredients WHERE r.id = :id")
    Recipe findByIdWithIngredients(@Param("id") Long id);
//
//    List<Recipe> findByTitleContainingIgnoreCaseOrIngredientsNameContainingIgnoreCase(String query, String query1);
//
//    List<Recipe> findByPreparationTimeBetween(Integer minTime, Integer maxTime);
//

    @Query("SELECT DISTINCT r FROM Recipe r JOIN r.ingredients i " +
            "WHERE (UPPER(r.title) LIKE %:query% OR UPPER(i.name) LIKE %:query%) " +
            "AND r.preparationTime BETWEEN :minTime AND :maxTime")
    List<Recipe> findByTitleAndPreparationTime(@Param("query") String query,
                                               @Param("minTime") int minTime,
                                               @Param("maxTime") int maxTime);

}
