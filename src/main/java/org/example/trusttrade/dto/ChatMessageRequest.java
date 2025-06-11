package org.example.trusttrade.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageRequest {
    private String roomId;
    private String senderId;
    private String content;
}