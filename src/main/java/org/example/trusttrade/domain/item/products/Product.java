package org.example.trusttrade.domain.item.products;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.trusttrade.domain.item.Item;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@DiscriminatorValue("PRODUCT")
@Table(name = "products")
public class Product extends Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Long id;

    @Column(name="product_price")
    private Integer productPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_status",nullable = false)
    private Product_status status;

}