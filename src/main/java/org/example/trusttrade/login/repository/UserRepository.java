package org.example.trusttrade.login.repository;

import org.example.trusttrade.login.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    Optional<User> findByBusinessNumber(String businessNumber);

    boolean existsByEmail(String email);

    boolean existsByBusinessNumber(String businessNumber);
}