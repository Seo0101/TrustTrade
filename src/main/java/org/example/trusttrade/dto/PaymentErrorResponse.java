package org.example.trusttrade.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentErrorResponse {
    private int code;
    private String message;
}
