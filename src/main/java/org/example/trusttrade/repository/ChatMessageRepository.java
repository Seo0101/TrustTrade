package org.example.trusttrade.repository;

import org.example.trusttrade.domain.chat.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}