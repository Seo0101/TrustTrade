package org.example.trusttrade.controller;

import lombok.RequiredArgsConstructor;
import org.example.trusttrade.dto.LoginResponse;
import org.example.trusttrade.service.KakaoLoginService;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class KakaoLoginController {

    private final KakaoLoginService kakaoLoginService;

    @GetMapping("/kakao/login")
    public ResponseEntity<LoginResponse> kakaoLogin(@RequestParam("code") String code) throws JSONException {
        String redirectUri = "http://localhost:8080/auth/kakao/login";
        LoginResponse response = kakaoLoginService.kakaoLogin(code, redirectUri);
        return ResponseEntity.ok(response);
    }
}