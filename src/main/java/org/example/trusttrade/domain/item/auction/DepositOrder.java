package org.example.trusttrade.domain.item.auction;

import jakarta.persistence.*;
import org.example.trusttrade.domain.User;

import java.time.LocalDateTime;

public class DepositOrder {

    private Long id;

    @OneToOne
    @JoinColumn(name = "auction_id", nullable = false)
    private Auction auction;

    @ManyToOne
    @JoinColumn(name = "bidder_id", nullable = false)
    private User bidder;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @Enumerated(EnumType.STRING)
    private DepositOrderStatus status;

    private LocalDateTime creationAt;
    private LocalDateTime updateAt;
}
