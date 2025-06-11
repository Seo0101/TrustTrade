package org.example.trusttrade.controller;

import lombok.RequiredArgsConstructor;
import org.example.trusttrade.service.BusinessVerifyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/business")
@RequiredArgsConstructor
public class BusinessVerifyController {

    private final BusinessVerifyService businessVerifyService;

    @PostMapping("/certify")
    public ResponseEntity<Void> certifyBusiness(
            @RequestParam UUID userId,
            @RequestParam String businessNumber,
            @RequestParam String startDt,
            @RequestParam String pNm
    ) {
        businessVerifyService.certifyBusiness(userId, businessNumber, startDt, pNm);
        return ResponseEntity.ok().build();
    }
}