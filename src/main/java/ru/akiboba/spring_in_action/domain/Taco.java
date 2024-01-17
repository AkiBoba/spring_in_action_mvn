package ru.akiboba.spring_in_action.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Класс содержит поля идентифицирующие характеристики Тако
 * @Ingredient
 */
@Data
@NoArgsConstructor
public class Taco {
    private Long id;
    private Date createdAt = new Date();
    private String name;
    private List<Ingredient> ingredients;

    public Taco(Long id, Date createdAt, String name) {
        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
    }
}