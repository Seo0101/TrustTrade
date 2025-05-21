package org.example.trusttrade.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import org.example.trusttrade.domain.User;
import org.example.trusttrade.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByBusinessNumber(String businessNumber) {
        return userRepository.findByBusinessNumber(businessNumber);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsByBusinessNumber(String businessNumber) {
        return userRepository.existsByBusinessNumber(businessNumber);
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void softDelete(UUID id) {
        userRepository.findById(id).ifPresent(user -> {
            user.setIsDeleted(true);
            user.setDeletedAt(java.time.LocalDateTime.now());
        });
    }
}