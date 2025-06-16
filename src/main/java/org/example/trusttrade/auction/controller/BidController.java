package org.example.trusttrade.auction.controller;

import lombok.RequiredArgsConstructor;
import org.example.trusttrade.auction.domain.Bids;
import org.example.trusttrade.auction.dto.BidsResponseDto;
import org.example.trusttrade.auction.dto.CreateBidReq;
import org.example.trusttrade.auction.repository.AuctionRepository;
import org.example.trusttrade.auction.repository.BidRepository;
import org.example.trusttrade.auction.service.BidService;
import org.example.trusttrade.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bids")
@RequiredArgsConstructor
public class BidController {

    @Autowired private final BidService bidService;
    @Autowired private final AuctionRepository auctionRepository;
    @Autowired private final UserRepository userRepository;
    @Autowired private BidRepository bidRepository;

    //bid 생성
    @PostMapping("/new")
    public ResponseEntity<?> createBid(CreateBidReq createBidReq) {

        Bids bid = bidService.creatBid
                (createBidReq.getUser(), createBidReq.getAuctionId(), createBidReq.getBidPrice());

        return ResponseEntity.ok(bid);
    }

    //top5 bidder 조회 by auctionId
    @GetMapping("/top5/{aucitonId}")
    public ResponseEntity<List<BidsResponseDto>> getTop5BidsByAuction(@PathVariable Long auctionId) {

        List<BidsResponseDto> top5 = bidService.getTop5BidsByAuction(auctionId);

        if (top5.isEmpty()) {
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok()
                    .header("X-Total-Count", String.valueOf(top5.size())) // 선택적으로 추가 정보 제공
                    .body(top5);
        }
    }

}
