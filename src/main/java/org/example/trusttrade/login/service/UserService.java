package org.example.trusttrade.login.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import org.example.trusttrade.login.domain.User;
import org.example.trusttrade.login.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    // user 권한 조회
    public User validateBusinessUser(UUID userId) {
        log.debug("validateBusinessUser 시작: userId={}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error("User 조회 실패: userId={} (EntityNotFoundException 발생)", userId);
                    return new EntityNotFoundException("회원 정보를 찾을 수 없습니다.");
                });

        log.debug("User 조회 성공: id={}, memberType={}", user.getId(), user.getMemberType());

        if (user.getMemberType() != User.MemberType.BUSINESS) {
            log.warn("권한 검증 실패: userId={}, memberType={} (BUSINESS 아님)", userId, user.getMemberType());
            throw new IllegalStateException("Business 회원만 상품 등록이 가능합니다.");
        }

        log.debug("권한 검증 통과: userId={} is BUSINESS", userId);
        return user;
    }

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
