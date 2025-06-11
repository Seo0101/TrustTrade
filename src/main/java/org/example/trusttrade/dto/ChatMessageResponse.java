package org.example.trusttrade.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChatMessageResponse {
    private String senderId;
    private String content;
    private String createdAt;
}