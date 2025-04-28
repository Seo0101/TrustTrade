package org.example.trusttrade.domain.item.auction;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.trusttrade.domain.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class DepositOrder {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "deposit_order_id")
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
    private DepositOrderStatus status = DepositOrderStatus.PENDING;

    //insert시 자동 생성
    @CreatedDate
    private LocalDateTime createdAt;
    //업데이트시 자동 생성
    @LastModifiedDate
    private LocalDateTime updateAt;
}
