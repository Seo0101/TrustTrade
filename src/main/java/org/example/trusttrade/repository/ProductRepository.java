package org.example.trusttrade.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.example.trusttrade.domain.User;
import org.example.trusttrade.domain.item.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository

public interface ProductRepository extends JpaRepository<Product, Long> {



}
