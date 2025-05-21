package org.example.trusttrade.service;

import lombok.RequiredArgsConstructor;
import org.example.trusttrade.client.TossPaymentClient;
import org.example.trusttrade.domain.order.Order;
import org.example.trusttrade.dto.ConfirmPaymentRequest;
import org.example.trusttrade.dto.OrderPaymentResDto;
import org.example.trusttrade.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Objects;


@Service
@RequiredArgsConstructor
@Transactional
public class PaymentService {

    private final OrderRepository orderRepository;
    private final TossPaymentClient tossPaymentClient;

    //결제 정보 검증
    public Order verifyPayment(OrderPaymentResDto orderPaymentResDto) {
        Long orderId = orderPaymentResDto.getOrderId();
        Order find = orderRepository.findById(orderId).orElse(null);

        if (find.getAmount() == orderPaymentResDto.getAmount()) {
            return find;
        }else {
            return null;
        }
    }

    //결제 정보 인증 api 호출
    public void confirmAndSavePayment(ConfirmPaymentRequest request) throws IOException, InterruptedException {
        HttpResponse<String> response = tossPaymentClient.requestConfirm(request);
        int statusCode = response.statusCode();
        String responseBody = response.body();

        if (statusCode == 200) {
            try {
                // 주문과 결제 조회 및 상태 변경
                processOrderAndPayment(request, true); // 결제 성공 시 처리

            } catch (Exception e) {
                // DB 처리 실패 → 결제 취소 요청
                HttpResponse cancelResponse = tossPaymentClient.requestPaymentCancel(
                        request.getPaymentKey(),
                        "결제 승인 후 DB 저장 실패로 인한 자동 취소");
                processOrderAndPayment(request, false);
            }
        } else {
            // Toss 응답 실패 (ex. 결제 키 오류, 금액 불일치 등)
            throw new IllegalStateException("결제 승인 실패: " + responseBody);
        }

    }

    // 주문과 결제 상태 변경을 처리하는 메서드
    private void processOrderAndPayment(ConfirmPaymentRequest request, boolean isSuccess) {
        try {

            Long orderId = 0L;
            // 주문 조회 및 상태 변경 >> 테스트용 주문 id 따로 설정해서 사용해야함
            if (Objects.equals(request.getOrderId(), "mT6-XV8OJGr83SzsFnHOO")) {
                orderId = 1L;
                Order order = orderRepository.findById(orderId)
                        .orElseThrow(() -> new IllegalArgumentException("해당 주문이 존재하지 않습니다."));

                if (isSuccess) {
                    order.paidOrder();  // 결제 성공 시 주문 상태 변경
                } else {
                    order.cancelOrder();  // 결제 실패 시 주문 상태 변경
                }
                orderRepository.save(order);
            }

        } catch (Exception ex) {
            throw new IllegalStateException("주문 또는 결제 상태 변경 중 오류 발생: " + ex.getMessage(), ex);
        }


    }
}
