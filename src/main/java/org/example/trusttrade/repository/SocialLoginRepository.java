package org.example.trusttrade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.example.trusttrade.domain.SocialLogin;

public interface SocialLoginRepository extends JpaRepository<SocialLogin, String> {
    Optional<SocialLogin> findByProviderAndProviderUserId(SocialLogin.Provider provider, String providerUserId);
}
