package org.example.trusttrade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;
import org.example.trusttrade.domain.item.order.Settlement;

public interface SettlementRepository extends JpaRepository<Settlement, Long> {

    List<Settlement> findBySellerId(UUID sellerId);

    List<Settlement> findByStatus(Settlement.Status status);
}