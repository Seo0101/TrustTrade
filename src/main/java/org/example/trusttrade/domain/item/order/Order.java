package org.example.trusttrade.domain.item.order;

import jakarta.persistence.*;
import org.example.trusttrade.domain.User;
import org.example.trusttrade.domain.item.products.Product;

import java.time.LocalDateTime;

public class Order {

    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id" ,nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private User buyer;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
