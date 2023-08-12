package com.example.int222_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.int222_backend.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
