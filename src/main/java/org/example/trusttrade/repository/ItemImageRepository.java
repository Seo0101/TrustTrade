package org.example.trusttrade.repository;

import org.example.trusttrade.domain.item.ItemImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemImageRepository extends JpaRepository<ItemImage,Integer> {
}
