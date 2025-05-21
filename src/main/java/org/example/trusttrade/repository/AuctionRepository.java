package org.example.trusttrade.repository;

import org.example.trusttrade.domain.item.auction.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionRepository extends JpaRepository<Auction,Integer> {
}
