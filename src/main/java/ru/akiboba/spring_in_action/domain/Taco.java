package ru.akiboba.spring_in_action.domain;

import lombok.Data;

import java.util.List;

@Data
public class Taco {

    private String name;

    private List<Ingredient> ingredients;

}