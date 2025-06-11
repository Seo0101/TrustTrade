package org.example.trusttrade.repository;

import org.example.trusttrade.auction.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AuctionRepository extends JpaRepository<Auction, Long> {


    @Query("SELECT a FROM Auction a WHERE a.user.id = :sellerId")
    List<Auction> getAuctionsBySellerId(@Param("sellerId") UUID sellerId);

}
