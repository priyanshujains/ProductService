package com.scalar.productservice.repositories;

import com.scalar.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface ProductRepo extends JpaRepository<Product,Long> {

    public List<Product> findTopByPriceBetween(double start, double end);

    public Optional<Product> findById(Long id);

    public Product save(Product product);

    public List<Product> findAll();
    public  void deleteById(Long id);
}
