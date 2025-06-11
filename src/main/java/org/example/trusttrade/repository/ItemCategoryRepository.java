package org.example.trusttrade.repository;

import org.example.trusttrade.domain.item.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCategoryRepository extends JpaRepository<ItemCategory,Integer> {
}
