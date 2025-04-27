package org.example.trusttrade.domain.item.auction;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.trusttrade.domain.item.Item;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Auction extends Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="auction_id")
    private Long id;

    @Column(name = "start_price",nullable = false)
    private Integer startPrice;

    @Column(name = "bid_unit",nullable = false)
    private Integer bidUnit;

    @Enumerated(EnumType.STRING)
    @Column(name = "auction_status",nullable = false)
    private Auction_status auctionStatus;

    @Column(name = "created_time",nullable = false)
    private LocalDateTime createdTime;

    @Column(name = "end_time",nullable = false)
    private LocalDateTime endTime;
}
