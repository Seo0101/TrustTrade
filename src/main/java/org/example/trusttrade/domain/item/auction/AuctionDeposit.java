package org.example.trusttrade.domain.item.auction;

import jakarta.persistence.*;
import org.example.trusttrade.domain.User;

import java.time.LocalDateTime;

public class AuctionDeposit {

    private Long id;

    @ManyToOne
    @JoinColumn(name = "auction_id", nullable = false)
    private Auction auction;

    @OneToOne
    @JoinColumn(name = "deposit_order_id", nullable = false)
    private DepositOrder depositOrder;

    @ManyToOne
    @JoinColumn(name = "bidder_id", nullable = false)
    private User bidder;

    @Enumerated(EnumType.STRING)
    private AuctionDepositStatus status;

    private int amount;

    private LocalDateTime createdAt;

    private LocalDateTime refundedAt;
}
