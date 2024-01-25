package ru.akiboba.spring_in_action.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Класс содержит поля идентифицирующие характеристики ингредиентов
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ingredients")
public class Ingredient {

    @Id
    private String id;

    private String name;

    public Ingredient(String id, String name, IngredientType type) {
        this.id = id;
        this.name = name;
    }

    public Ingredient(String id) {
        this.id = id;
    }
}
