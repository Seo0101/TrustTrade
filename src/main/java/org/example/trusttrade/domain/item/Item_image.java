package org.example.trusttrade.domain.item;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Item_image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="item_image_id")
    private Long id;

    @Column(name = "image")
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "item_id",nullable = false)
    private Item item;

    @Column(name = "saved_time",nullable = false)
    private LocalDateTime savedTime;

}
