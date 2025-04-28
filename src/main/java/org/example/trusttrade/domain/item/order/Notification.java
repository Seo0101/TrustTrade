package org.example.trusttrade.domain.item.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.trusttrade.domain.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter @Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "notification_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(length = 255)
    private String content;

    @CreatedDate
    private LocalDateTime createdAt;

    private boolean isRead;
}
