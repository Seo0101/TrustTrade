package org.example.trusttrade.domain.item.products;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product_location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_location_id")
    private Long id;

    @Column(name = "latitude",nullable = false)
    private double latitude;

    @Column(name = "logtitude",nullable = false)
    private double longtitude;

    @Column(name = "address",length = 100,nullable = false)
    private String address;

}
