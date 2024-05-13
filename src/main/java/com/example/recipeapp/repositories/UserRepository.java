package com.example.recipeapp.repositories;

import com.example.recipeapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User findByUsername(String username);
    User findFirstByUsername(String username);
}
