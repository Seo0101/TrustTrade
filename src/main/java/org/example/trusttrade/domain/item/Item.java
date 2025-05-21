package org.example.trusttrade.domain.item;

import jakarta.persistence.*;
import lombok.*;
import org.example.trusttrade.domain.User;
import org.example.trusttrade.domain.item.products.Product_location;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private User user;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "description", length = 200, nullable = false)
    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trade_place")
    private Product_location productLocation;

    @Column(name = "created_time", updatable = false)
    private LocalDateTime createdTime;

}
