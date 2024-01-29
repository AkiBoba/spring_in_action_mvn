package ru.akiboba.spring_in_action.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akiboba.spring_in_action.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
