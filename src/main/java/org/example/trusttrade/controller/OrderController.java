package org.example.trusttrade.controller;

import lombok.RequiredArgsConstructor;
import org.example.trusttrade.domain.order.Order;
import org.example.trusttrade.dto.OrderPaymentResDto;
import org.example.trusttrade.dto.OrderReqDto;
import org.example.trusttrade.dto.SaveAmountRequest;
import org.example.trusttrade.service.OrderService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    //order 생성
    @PostMapping("/new")
    public ResponseEntity<OrderPaymentResDto> createOrder(@RequestBody OrderReqDto request) {

        Order order = orderService.createOrder(request);

        //필수 설정 : amount - currency, value / orderId/ orderName
        OrderPaymentResDto response = new OrderPaymentResDto(
                order.getId(),
                order.getAmount(),
                order.getProductName());

        return ResponseEntity.ok(response);

    }

    //상품 수령시 order 상태 변경
    // 수령 완료 클릭 시 주문 상태를 COMPLETED로 변경
    @PostMapping("/{orderId}/complete")
    public ResponseEntity<?> completeOrder(@PathVariable Long orderId) {
        try {
            orderService.completeOrder(orderId);
            return ResponseEntity.ok("주문 수령 완료 처리되었습니다.");
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //구매 목록 조회
    @GetMapping("/{userId}/list")
    public ResponseEntity<?> getOrders(@PathVariable UUID userId) {
        List<Order> orders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }


}
