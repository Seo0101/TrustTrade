package org.example.trusttrade.order.dto;


import lombok.Getter;
import lombok.Setter;

//결제 정보 임시 저장을 위한 엔티티
@Getter @Setter
public class SaveAmountRequest {
    private Long orderId;
    private int amount;
}
