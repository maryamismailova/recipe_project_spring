package com.recipes.recipeproject.domain;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String recipeNotes;
    @OneToOne(mappedBy = "notes")
//    @OneToOne
    private Recipe recipe;

}
