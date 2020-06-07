package com.recipes.recipeproject.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    @Lob
    private String directions;

    @Enumerated(value=EnumType.STRING)
    private Difficulty difficulty;
    @Lob
    private Byte[] image;

    /***
     *Cascade type All - if a recipe is deleted/modified all the ingredients linked to it
     * are also modified with it
     *
     * Recipe is a parent object!
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe", fetch = FetchType.EAGER)
    Set<Ingredient> ingredients= new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @ManyToMany
    @JoinTable(name = "recipe_category",
    joinColumns = @JoinColumn(name="recipe_id"),
            inverseJoinColumns = @JoinColumn( name= "category_id"))
    private Set<Category> categories= new HashSet<>();

    public void setNotes(Notes notes) {
        notes.setRecipe(this);
        this.notes = notes;
    }
    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }
}
