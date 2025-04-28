package org.example.trusttrade.domain.item.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter @Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Payment {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id" ,nullable = false)
    private Order order;

    @Column(unique = true, nullable = false)
    private String paymentKey;

    private int amount;

    @Enumerated(EnumType.STRING)
    private Status status = Status.READY;

    @CreatedDate
    private LocalDateTime requestAt;
    @LastModifiedDate
    private LocalDateTime approvedAt;

    public enum Status{
        READY,IN_PROGRESS, SUCCESS, FAILED, EXPIRED
    }
}

