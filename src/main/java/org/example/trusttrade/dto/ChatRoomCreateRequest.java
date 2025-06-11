package org.example.trusttrade.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomCreateRequest {
    private String sellerId;
    private String buyerId;
    private String itemId;
}