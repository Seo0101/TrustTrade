package org.example.trusttrade.repository;

import org.example.trusttrade.domain.item.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
