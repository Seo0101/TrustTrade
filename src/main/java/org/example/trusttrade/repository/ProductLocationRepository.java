package org.example.trusttrade.repository;

import org.example.trusttrade.domain.item.products.Product_location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductLocationRepository extends JpaRepository<Product_location, Long> {
}
