package ru.akiboba.spring_in_action.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.akiboba.spring_in_action.domain.TacoOrder;
import ru.akiboba.spring_in_action.domain.User;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<TacoOrder, Long> {
    List<TacoOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
