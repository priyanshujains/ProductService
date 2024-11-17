package com.scalar.productservice.repositories;

import com.scalar.productservice.models.Category;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {

        public Optional<Category> findByName(String name);

        public Category save(Category category);
}
