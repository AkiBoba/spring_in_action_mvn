package ru.akiboba.spring_in_action.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.akiboba.spring_in_action.domain.Ingredient;

import java.util.HashMap;
import java.util.Map;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private Map<String, Ingredient> ingredientMap = new HashMap<>();

    @Override
    public Ingredient convert(String id) {
        return ingredientMap.get(id);
    }

}
