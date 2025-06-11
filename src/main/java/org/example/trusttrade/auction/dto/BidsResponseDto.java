package org.example.trusttrade.auction.dto;

import lombok.Data;
import org.example.trusttrade.auction.domain.Bids;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BidsResponseDto {
    private Long id;
    private UUID bidderId;
    private int bidPrice;
    private LocalDateTime createdTime;

    public static BidsResponseDto from(Bids bid) {
        BidsResponseDto dto = new BidsResponseDto();
        dto.setId(bid.getId());
        dto.setBidderId(bid.getUser().getId());
        dto.setBidPrice(bid.getBidPrice());
        dto.setCreatedTime(bid.getCreatedTime());
        return dto;
    }
}

