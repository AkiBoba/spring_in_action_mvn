package ru.akiboba.spring_in_action.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Класс содержит поля идентифицирующие характеристики ингредиентов
 */
@Data
@AllArgsConstructor
public class Ingredient {

    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

}
