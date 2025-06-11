package org.example.trusttrade.item.repository;

import org.example.trusttrade.item.domain.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCategoryRepository extends JpaRepository<ItemCategory,Integer> {
}
