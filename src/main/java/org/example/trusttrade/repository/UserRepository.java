package org.example.trusttrade.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.example.trusttrade.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;
import org.example.trusttrade.domain.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    Optional<User> findByBusinessNumber(String businessNumber);

    boolean existsByEmail(String email);

    boolean existsByBusinessNumber(String businessNumber);
}