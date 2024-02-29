package ru.akiboba.spring_in_action.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.akiboba.spring_in_action.domain.IngredientRef;

import java.util.List;

@Repository
public interface IngredientRefRepository extends JpaRepository<IngredientRef, Long> {
    List<IngredientRef> findAllByTaco(long id);
}
