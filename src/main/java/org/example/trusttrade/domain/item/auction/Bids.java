package org.example.trusttrade.domain.item.auction;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.trusttrade.domain.User;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Bids {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bids_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "auction_id",nullable = false)
    private Auction auction;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "bidder_id",nullable = false)
    private User user;

    @Column(name = "created_time",nullable = false)
    private LocalDateTime createdTime;



}
