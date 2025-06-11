package org.example.trusttrade.item.repository;

import org.example.trusttrade.item.domain.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProductRepository extends JpaRepository<Product, Long> {}


