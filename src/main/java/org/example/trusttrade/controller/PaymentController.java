package org.example.trusttrade.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.trusttrade.domain.order.Order;
import org.example.trusttrade.domain.order.Payment;
import org.example.trusttrade.dto.*;
import org.example.trusttrade.service.OrderService;
import org.example.trusttrade.service.PaymentService;
import org.example.trusttrade.client.TossPaymentClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

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

    //임시 결제 정보 저장
    @PostMapping("/saveAmount")
    public ResponseEntity<?> saveAmount(HttpSession httpSession, @RequestBody SaveAmountRequest saveAmountRequest) {

        //결제 정보 검증을 위한 세션에 임시 값 저장
        httpSession.setAttribute(saveAmountRequest.getOrderId().toString(), saveAmountRequest.getAmount());
        return ResponseEntity.ok("Payment temp save successful");
    }


    //임시 결제 정보 검증
    @PostMapping("/verifyAmount")
    public ResponseEntity<?> verifyAmount(HttpSession httpSession, @RequestBody SaveAmountRequest saveAmountRequest) {

        Integer amount = (Integer) httpSession.getAttribute(saveAmountRequest.getOrderId().toString());

        if (amount == null || !amount.equals(saveAmountRequest.getAmount())) {
            return ResponseEntity.badRequest().body(PaymentErrorResponse.builder()
                    .code(400)
                    .message("결제 금액 정보가 유효하지 않습니다.")
                    .build());
        }
        httpSession.removeAttribute(saveAmountRequest.getOrderId().toString());

        return ResponseEntity.ok("Payment is valid");
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