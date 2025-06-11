package org.example.trusttrade.domain.item.auction;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.trusttrade.login.domain.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class AuctionDeposit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "auction_deposit_id")
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
    private Status status = Status.DEPOSITED;

    private int amount;

    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private LocalDateTime refundedAt;

    public enum Status{
        DEPOSITED, REFUNDED
    }
}
