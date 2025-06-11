package org.example.trusttrade.repository;

import org.example.trusttrade.domain.item.auction.Bids;
import org.example.trusttrade.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bids, Long> {

    // 금액 기준으로 Top 5 조회
    List<Bids> findTop5ByAuctionIdOrderByBidPriceDesc(Long auctionId);
}
