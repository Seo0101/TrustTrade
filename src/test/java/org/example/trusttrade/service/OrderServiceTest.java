package org.example.trusttrade.service;


import org.example.trusttrade.domain.User;
import org.example.trusttrade.domain.item.products.Product;
import org.example.trusttrade.domain.item.products.Product_location;
import org.example.trusttrade.domain.item.products.Product_status;
import org.example.trusttrade.domain.order.Order;
import org.example.trusttrade.dto.ConfirmPaymentRequest;
import org.example.trusttrade.dto.OrderReqDto;
import org.example.trusttrade.repository.OrderRepository;
import org.example.trusttrade.repository.ProductLocationRepository;
import org.example.trusttrade.repository.ProductRepository;
import org.example.trusttrade.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductLocationRepository productLocationRepository;

    Long dbOrderId;
    @Autowired
    private PaymentService paymentService;

    //order 생성 테스트
    //User, Product 생성도 같이 완료
    @Test
    public void createOrder() {

        User buyer = User.builder()
                .id(UUID.randomUUID())
                .email("hyunjiin@google.com")
                .profileImage("img")
                .role(User.Role.USER)
                .memberType(User.MemberType.BUSINESS)
                .businessNumber("02-123-1234")
                .createdAt(LocalDateTime.now())
                .roughAddress("suwonsi")
                .build();

        User seller = User.builder()
                .id(UUID.randomUUID())
                .email("hyunjin2@google.com")
                .profileImage("img2")
                .role(User.Role.USER)
                .memberType(User.MemberType.BUSINESS)
                .businessNumber("02-123-12345")
                .createdAt(LocalDateTime.now())
                .roughAddress("suwonsi")
                .build();

        userRepository.save(buyer);
        userRepository.save(seller);


        Product_location location = Product_location.builder()
                .latitude(2.3)
                .longtitude(5.5)
                .address("seoulsi")
                .build();

        productLocationRepository.save(location);

        Product product = new Product();
        product.setProductPrice(200000);
        product.setStatus(Product_status.SALE);
        product.setUser(seller);
        product.setName("원목 의자 20개");
        product.setDescription("사용감 x, 직거래만 가능");
        product.setProductLocation(location);
        product.setCreatedTime(LocalDateTime.now());

        productRepository.save(product);

        System.out.println("given 성공");


        //when
        OrderReqDto orderReqDto = new OrderReqDto();
        orderReqDto.setBuyerId(buyer.getId());
        orderReqDto.setSellerId(seller.getId());
        orderReqDto.setProductId(product.getId());

        Order order = orderService.createOrder(orderReqDto);

        System.out.println("when 성공");

        //then
        Order found = orderRepository.findById(order.getId()).orElseThrow();
        assertEquals(order.getId(), found.getId());
        System.out.println("then 성공");
        dbOrderId = order.getId();
        System.out.println(dbOrderId);
    }

    //주문 수령 상태 변경 테스트
    @Test
    public void completeOrder() {

        User buyer = User.builder()
                .id(UUID.randomUUID())
                .email("hyunjiin@google.com")
                .profileImage("img")
                .role(User.Role.USER)
                .memberType(User.MemberType.BUSINESS)
                .businessNumber("02-123-1234")
                .createdAt(LocalDateTime.now())
                .roughAddress("suwonsi")
                .build();

        User seller = User.builder()
                .id(UUID.randomUUID())
                .email("hyunjin2@google.com")
                .profileImage("img2")
                .role(User.Role.USER)
                .memberType(User.MemberType.BUSINESS)
                .businessNumber("02-123-12345")
                .createdAt(LocalDateTime.now())
                .roughAddress("suwonsi")
                .build();

        userRepository.save(buyer);
        userRepository.save(seller);


        Product_location location = Product_location.builder()
                .latitude(2.3)
                .longtitude(5.5)
                .address("seoulsi")
                .build();

        productLocationRepository.save(location);

        Product product = new Product();
        product.setProductPrice(200000);
        product.setStatus(Product_status.SALE);
        product.setUser(seller);
        product.setName("원목 의자 20개");
        product.setDescription("사용감 x, 직거래만 가능");
        product.setProductLocation(location);
        product.setCreatedTime(LocalDateTime.now());

        productRepository.save(product);


        OrderReqDto orderReqDto = new OrderReqDto();
        orderReqDto.setBuyerId(buyer.getId());
        orderReqDto.setSellerId(seller.getId());
        orderReqDto.setProductId(product.getId());

        Order order = orderService.createOrder(orderReqDto);

        order.setStatus(Order.Status.PAID);
        System.out.println("given 성공");

        orderService.completeOrder(order.getId());

        //PAID 상태인 경우, 아닌 경우 모두 테스트 성공
        if (order.getStatus() == Order.Status.COMPLETED) {
            System.out.println("complete 변경 성공");
        } else {
            System.out.println("complete 변경 실패");
        }

    }

    //결제 성공/실패시 order 상태 변경 체크
    @Test
    public void successOrder() throws IOException, InterruptedException {


        User buyer = User.builder()
                .id(UUID.randomUUID())
                .email("hyunjiin@google.com")
                .profileImage("img")
                .role(User.Role.USER)
                .memberType(User.MemberType.BUSINESS)
                .businessNumber("02-123-1234")
                .createdAt(LocalDateTime.now())
                .roughAddress("suwonsi")
                .build();

        User seller = User.builder()
                .id(UUID.randomUUID())
                .email("hyunjin2@google.com")
                .profileImage("img2")
                .role(User.Role.USER)
                .memberType(User.MemberType.BUSINESS)
                .businessNumber("02-123-12345")
                .createdAt(LocalDateTime.now())
                .roughAddress("suwonsi")
                .build();

        userRepository.save(buyer);
        userRepository.save(seller);


        Product_location location = Product_location.builder()
                .latitude(2.3)
                .longtitude(5.5)
                .address("seoulsi")
                .build();

        productLocationRepository.save(location);

        Product product = new Product();
        product.setProductPrice(200000);
        product.setStatus(Product_status.SALE);
        product.setUser(seller);
        product.setName("원목 의자 20개");
        product.setDescription("사용감 x, 직거래만 가능");
        product.setProductLocation(location);
        product.setCreatedTime(LocalDateTime.now());

        productRepository.save(product);


        OrderReqDto orderReqDto = new OrderReqDto();
        orderReqDto.setBuyerId(buyer.getId());
        orderReqDto.setSellerId(seller.getId());
        orderReqDto.setProductId(product.getId());

        Order order = orderService.createOrder(orderReqDto);

        System.out.println("given 성공");

        ConfirmPaymentRequest request = new ConfirmPaymentRequest();
        request.setAmount(String.valueOf(order.getAmount()));
        request.setOrderId(String.valueOf(order.getId()));
        request.setPaymentKey("paymentKet");

        paymentService.confirmAndSavePayment(request);
        System.out.println("when 성공");

        if (order.getStatus() == Order.Status.PAID) {
            System.out.println("then 성공");
        } else {
            System.out.println("then 실패");
        }

    }

    //주문목록 조회
    @Test
    @Rollback(false)
    public void conformOrder() {
        User buyer = User.builder()
                .id(UUID.randomUUID())
                .email("hyunjiin@google.com")
                .profileImage("img")
                .role(User.Role.USER)
                .memberType(User.MemberType.BUSINESS)
                .businessNumber("02-123-1234")
                .createdAt(LocalDateTime.now())
                .roughAddress("suwonsi")
                .build();

        User seller = User.builder()
                .id(UUID.randomUUID())
                .email("hyunjin2@google.com")
                .profileImage("img2")
                .role(User.Role.USER)
                .memberType(User.MemberType.BUSINESS)
                .businessNumber("02-123-12345")
                .createdAt(LocalDateTime.now())
                .roughAddress("suwonsi")
                .build();

        userRepository.save(buyer);
        userRepository.save(seller);


        Product_location location = Product_location.builder()
                .latitude(2.3)
                .longtitude(5.5)
                .address("seoulsi")
                .build();

        productLocationRepository.save(location);

        Product product = new Product();
        product.setProductPrice(200000);
        product.setStatus(Product_status.SALE);
        product.setUser(seller);
        product.setName("원목 의자 20개");
        product.setDescription("사용감 x, 직거래만 가능");
        product.setProductLocation(location);
        product.setCreatedTime(LocalDateTime.now());

        productRepository.save(product);


        OrderReqDto orderReqDto = new OrderReqDto();
        orderReqDto.setBuyerId(buyer.getId());
        orderReqDto.setSellerId(seller.getId());
        orderReqDto.setProductId(product.getId());

        Order order = orderService.createOrder(orderReqDto);
        System.out.println("given 성공");

        Order find = orderRepository.findById(order.getId()).orElse(null);
        assertEquals(order, find);
        System.out.println("assert 성공");


        List<Order> orders = orderService.getOrdersByBuyerId(find.getBuyer().getId());

        for (Order order1 : orders) {
            System.out.println(order1.getProductName());
        }

    }


}
