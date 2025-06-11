package org.example.trusttrade.order.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderCancelDto {
    String orderId;
    String paymentKey;
    String reason;
}
