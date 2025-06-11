package org.example.trusttrade.login.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KakaoUserInfoDto {
    private String email;
    private String name;
    private String profileImageUrl;
    private String kakaoId;
}