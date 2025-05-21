package org.example.trusttrade.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.trusttrade.domain.order.Order;
import org.example.trusttrade.dto.*;
import org.example.trusttrade.service.OrderService;
import org.example.trusttrade.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final OrderService orderService;

    //결제 정보 검증
    @PostMapping("/verifyPayment")
    public ResponseEntity<?> verifyPayment(@RequestBody OrderPaymentResDto orderPaymentResDto) {

        Order order = paymentService.verifyPayment(orderPaymentResDto);
        if (order == null) {
            return ResponseEntity.badRequest().body(PaymentErrorResponse.builder()
                    .code(400)
                    .message("결제 금액 정보가 유효하지 않습니다.")
                    .build());
        } else {
            return ResponseEntity.ok("success");
        }
    }

    @PostMapping("/confirm")
    public ResponseEntity confirm(@RequestBody ConfirmPaymentRequest confirmPaymentRequest) {
        try {

            paymentService.confirmAndSavePayment(confirmPaymentRequest);
            return ResponseEntity.ok("결제 승인 및 저장 성공");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    PaymentErrorResponse.builder()
                            .code(400)
                            .message("결제 승인 중 에러 발생")
                            .build()
            );
        }
    }
}