package org.example.trusttrade.domain.item.order;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.example.trusttrade.domain.User;

import java.time.LocalDateTime;

public class Notification {

    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String content;

    private LocalDateTime createdAt;

    private boolean isLead;
}
