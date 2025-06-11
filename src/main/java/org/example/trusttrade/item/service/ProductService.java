package org.example.trusttrade.item.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.trusttrade.item.domain.products.Product;
import org.example.trusttrade.item.dto.request.BasicItemDto;
import org.example.trusttrade.item.repository.ItemRepository;
import org.example.trusttrade.item.repository.ProductRepository;
import org.example.trusttrade.login.domain.User;
import org.example.trusttrade.login.service.UserService;

import org.example.trusttrade.login.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ItemService itemService;
    private final UserService userService;
    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Transactional
    public void registerProduct(BasicItemDto dto, User seller) {
        // 1) Product 객체 생성
        Product product = Product.fromDto(dto, seller);
        log.debug("Product 객체 생성 완료: {}", product);
        productRepository.save(product);
        log.debug("Product 저장 완료: itemId={}", product.getId());

        // 2) 이미지·카테고리 저장
        itemService.saveItemDetails(product, dto.getImages(), dto.getCategoryIds());
        log.debug("이미지·카테고리 저장 완료: itemId={}", product.getId());
    }

}
