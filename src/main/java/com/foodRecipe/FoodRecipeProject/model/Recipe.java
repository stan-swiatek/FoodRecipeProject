package com.foodRecipe.FoodRecipeProject.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Column(name = "instructions", length = 1000)
    @Lob
    private String instructions;
    private Integer preparationTime;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_id")
    @Column(name = "ingredients")
    private List<Ingredient> ingredients = new ArrayList<>();





}
