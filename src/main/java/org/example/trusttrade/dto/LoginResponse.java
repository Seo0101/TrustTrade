package org.example.trusttrade.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private String email;
    private String nickname;
    private String profileImageUrl;
    private boolean isRegistered;
}