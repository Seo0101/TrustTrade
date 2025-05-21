package org.example.trusttrade.repository;

import org.example.trusttrade.domain.item.products.ProductLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ProductLocationRepository extends JpaRepository<ProductLocation,Integer> {
}
