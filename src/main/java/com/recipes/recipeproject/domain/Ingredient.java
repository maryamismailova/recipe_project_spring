package com.recipes.recipeproject.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String description;
    private BigDecimal amount;

    @ManyToOne
    private Recipe recipe;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uof;

    public Ingredient(){

    }

    public Ingredient(String description, BigDecimal amount,  UnitOfMeasure unitOfMeasure, Recipe recipe) {
        this.description=description;
        this.amount=amount;
        this.recipe=recipe;
        this.uof=unitOfMeasure;
    }
    public Ingredient(String description, BigDecimal amount,  UnitOfMeasure unitOfMeasure) {
        this.description=description;
        this.amount=amount;
        this.uof=unitOfMeasure;
    }

    @Override
    public String toString() {
        return ""+description + '\'' +
                " - " + amount +" "+uof.getDescription();
    }
}
