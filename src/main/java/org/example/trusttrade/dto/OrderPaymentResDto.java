package org.example.trusttrade.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderPaymentResDto {

    private Long orderId;
    private int amount;
    private String productName;

}
