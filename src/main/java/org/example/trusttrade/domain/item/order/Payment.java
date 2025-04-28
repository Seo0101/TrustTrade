package org.example.trusttrade.domain.item.order;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.time.LocalDateTime;

public class Payment {

    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id" ,nullable = false)
    private Order order;

    private String paymentKey;

    private int amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private LocalDateTime requestAt;
    private LocalDateTime approvedAt;
}


