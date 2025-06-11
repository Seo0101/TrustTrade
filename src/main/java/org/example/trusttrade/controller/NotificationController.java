package org.example.trusttrade.controller;

import lombok.RequiredArgsConstructor;
import org.example.trusttrade.domain.order.Notification;
import org.example.trusttrade.dto.NotificationForm;
import org.example.trusttrade.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;


    //알림 생성
    @PostMapping("/new")
    public ResponseEntity<?> createNotification(@RequestBody NotificationForm request) {
        Notification noti = notificationService.createNotification(request);
        return ResponseEntity.ok(noti);
    }

    //알림 조회
    @GetMapping("/{userId}/list")
    public ResponseEntity<List<Notification>> getNotifications(@PathVariable UUID userId) {
        List<Notification> noties = notificationService.getnotiesByUserId(userId);
        return ResponseEntity.ok(noties);
    }



}
