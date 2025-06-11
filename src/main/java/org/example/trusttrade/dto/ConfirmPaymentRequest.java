package org.example.trusttrade.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmPaymentRequest {
    private String amount;
    private String orderId;
    private String paymentKey;
}
