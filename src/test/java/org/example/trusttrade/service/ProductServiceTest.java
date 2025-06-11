/*
package org.example.trusttrade.service;

import jakarta.transaction.Transactional;
import org.example.trusttrade.domain.User;
import org.example.trusttrade.domain.item.Category;
import org.example.trusttrade.domain.item.products.ProductLocation;
import org.example.trusttrade.dto.BasicItemDto;
import org.example.trusttrade.repository.CategoryRepository;
import org.example.trusttrade.repository.ProductLocationRepository;
import org.example.trusttrade.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductLocationRepository productLocationRepository;

    @Test
    void registerProduct_success() {
        // 1. 사용자 생성
        User user = User.builder()
                .email("seller@example.com")
                .profileImage("https://example.com/profile.jpg")
                .role(User.Role.USER)
                .memberType(User.MemberType.GENERAL)
                .roughAddress("서울시 마포구")
                .build();
        userRepository.save(user);

        // 2. 카테고리 생성
        Category cat1 = categoryRepository.save(Category.builder().categoryName("가전제품").build());
        Category cat2 = categoryRepository.save(Category.builder().categoryName("생활용품").build());

        // 3. DTO 구성
        BasicItemDto dto = new BasicItemDto();
        dto.setName("다이슨 청소기");
        dto.setDescription("2022년식 다이슨 V10, 배터리 상태 양호합니다.");
        dto.setSellerId(user.getId());
        dto.setCategoryIds(List.of(cat1.getId().intValue(), cat2.getId().intValue()));
        dto.setImages(List.of(
                "https://cdn.example.com/image1.jpg",
                "https://cdn.example.com/image2.jpg"
        ));
        dto.setPrice(320000);

        dto.setAddress("서울특별시 마포구 연남동 227-16");
        dto.setLatitude(37.5614);
        dto.setLongitude(126.9257);

        // 4. 서비스 실행
        productService.registerProduct(dto,user);

        // 5. 결과 검증
        System.out.println("일반 상품 등록 테스트 성공");
    }
}*/
