package org.example.trusttrade.domain.item;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.trusttrade.domain.item.Category;
import org.example.trusttrade.domain.item.Item;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Item_category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="item_category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name="item_id",nullable = false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name="category_id",nullable = false)
    private Category category;
}
