package org.example.trusttrade.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmPaymentRequest {
    private int amount;
    private String orderId;
    private String paymentKey;
}
