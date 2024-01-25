package ru.akiboba.spring_in_action.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akiboba.spring_in_action.domain.IngredientType;

public interface IngredientTypeRepository extends JpaRepository<IngredientType, Integer> {
}
