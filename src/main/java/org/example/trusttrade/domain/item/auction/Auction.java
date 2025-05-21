package org.example.trusttrade.domain.item.auction;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.trusttrade.domain.User;
import org.example.trusttrade.domain.item.Item;
import org.example.trusttrade.domain.item.products.ProductLocation;
import org.example.trusttrade.dto.AuctionItemDto;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
@DiscriminatorValue("AUCTION")
@Table(name = "auction")
public class Auction extends Item {

    @Column(name = "start_price",nullable = false)
    private Integer startPrice;

    @Column(name = "bid_unit",nullable = false)
    private Integer bidUnit;

    @Enumerated(EnumType.STRING)
    @Column(name = "auction_status",nullable = false)
    private AuctionStatus auctionStatus;

    @Column(name = "end_time",nullable = false)
    private LocalDateTime endTime;

    public static Auction fromDto(AuctionItemDto dto, User seller){
        // 위치 정보 생성/조회
        ProductLocation loc = ProductLocation.fromDto(dto);

        // 3) Auction 엔티티
        return Auction.builder()
                // Item
                .user(seller)
                .name(dto.getName())
                .description(dto.getDescription())
                .productLocation(loc)
                .createdTime(LocalDateTime.now())
                // Auction
                .startPrice(dto.getStartPrice())
                .bidUnit(dto.getBidUnit())
                .auctionStatus(AuctionStatus.OPEN)
                .endTime(dto.getEndTime())
                .build();
    }
}
