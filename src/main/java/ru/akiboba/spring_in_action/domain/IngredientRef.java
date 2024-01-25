package ru.akiboba.spring_in_action.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "tacos_ref_ingredients")
public class IngredientRef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long taco;
    private String ingredient;

    public IngredientRef(long taco, String ingredient) {
        this.taco = taco;
        this.ingredient = ingredient;
    }
}
