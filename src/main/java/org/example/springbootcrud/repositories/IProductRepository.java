package org.example.springbootcrud.repositories;

import org.example.springbootcrud.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product,Long> {
}
