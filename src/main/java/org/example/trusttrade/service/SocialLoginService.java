package org.example.trusttrade.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import org.example.trusttrade.domain.SocialLogin;
import org.example.trusttrade.repository.SocialLoginRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SocialLoginService {

    private final SocialLoginRepository socialLoginRepository;

    public Optional<SocialLogin> findByProviderAndUserId(SocialLogin.Provider provider, String providerUserId) {
        return socialLoginRepository.findByProviderAndProviderUserId(provider, providerUserId);
    }

    public Optional<SocialLogin> findById(String userId) {
        return socialLoginRepository.findById(userId);
    }

    @Transactional
    public SocialLogin save(SocialLogin login) {
        return socialLoginRepository.save(login);
    }
}