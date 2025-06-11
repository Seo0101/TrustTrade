package org.example.trusttrade.domain.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.trusttrade.domain.User;
import org.example.trusttrade.domain.item.products.Product;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter @Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id" ,nullable = false)
    private Product product;

    private int amount;

    @Column(unique = true)
    private String paymentKey;

    @Column(nullable = false)
    private String productName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id", nullable = false)
    private User buyer;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public enum Status{
        PENDING, PAID, COMPLETED, CANCELLED
    }

    //order 생성
    public static Order create(Product product, User seller, User buyer) {
        Order order = new Order();
        order.amount = product.getProductPrice();
        order.productName = product.getName();
        order.product = product;
        order.seller = seller;
        order.buyer = buyer;

        return order;
    }


    //결제 완료 상태
    public void paidOrder() {
        if (this.status != Status.PENDING) {
            throw new IllegalStateException("PENDING 상태가 아니면 결제 완료 처리할 수 없습니다.");
        }
        this.status = Status.PAID;
    }
    // 수령 완료 상태로 변경
    public void completeOrder() {
        if (this.status != Status.PAID) {
            throw new IllegalStateException("결제 완료 상태가 아니면 수령 완료 처리할 수 없습니다.");
        }
        this.status = Status.COMPLETED;
    }

    //결제 취소될 경우 상태
    public void cancelOrder() {
        if(this.status != Status.PENDING) {
            throw new IllegalStateException("pending 상태가 아니면 취소 처리가 불가능합니다.");
        }
        this.status = Status.CANCELLED;
    }


}