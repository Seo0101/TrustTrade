package org.example.trusttrade.service;


import lombok.RequiredArgsConstructor;
import org.example.trusttrade.domain.User;
import org.example.trusttrade.domain.order.Notification;
import org.example.trusttrade.dto.NotificationForm;
import org.example.trusttrade.repository.NotificationRepository;
import org.example.trusttrade.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public Notification createNotification(NotificationForm request) {

        String content = request.getContent();
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + request.getUserId()));

        Notification noti = Notification.create(user, content);

        notificationRepository.save(noti);
        return noti;
    }

    public List<Notification> getnotiesByUserId(UUID userId) {
        List<Notification> noties = notificationRepository.getNotiesByUserId(userId);
        return noties;
    }
}
