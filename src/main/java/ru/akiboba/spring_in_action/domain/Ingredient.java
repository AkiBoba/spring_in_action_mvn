package ru.akiboba.spring_in_action.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Класс содержит поля идентифицирующие характеристики ингредиентов
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "ingredients")
public class Ingredient {

    @Id
    private String id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private IngredientType type;

    public Ingredient(String id, String name, IngredientType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Ingredient(String id) {
        this.id = id;
    }
}
