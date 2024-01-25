package ru.akiboba.spring_in_action.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.akiboba.spring_in_action.domain.TacoOrder;

@Repository
public interface OrderRepository extends JpaRepository<TacoOrder, Long> {
}
