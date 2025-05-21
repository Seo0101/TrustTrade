package org.example.trusttrade.repository;

import org.example.trusttrade.domain.item.products.ProductLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLocationRepository extends JpaRepository<ProductLocation,Integer> {
}
