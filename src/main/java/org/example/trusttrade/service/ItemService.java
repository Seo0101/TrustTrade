package org.example.trusttrade.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.trusttrade.domain.User;
import org.example.trusttrade.domain.item.Category;
import org.example.trusttrade.domain.item.Item;
import org.example.trusttrade.domain.item.ItemCategory;
import org.example.trusttrade.domain.item.ItemImage;
import org.example.trusttrade.domain.item.products.Product;
import org.example.trusttrade.domain.item.products.ProductLocation;
import org.example.trusttrade.dto.AuctionItemDto;
import org.example.trusttrade.dto.BasicItemDto;
import org.example.trusttrade.dto.ItemResponseDto;
import org.example.trusttrade.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemService {

    private final ItemImageRepository itemImageRepository;
    private final CategoryRepository categoryRepository;
    private final ItemCategoryRepository itemCategoryRepository;
    private final ItemRepository itemRepository;
    private final CategoryService categoryService;
    private final ProductRepository productRepository;
    private final AuctionRepository auctionRepository;

    @Transactional
    public void saveItemDetails(Item item, List<String> imageUrls, List<Integer> categoryIds) {
        log.debug("saveItemDetails 시작: itemId={}, imageCount={}, categoryCount={}",
                item.getId(),
                imageUrls == null ? 0 : imageUrls.size(),
                categoryIds == null ? 0 : categoryIds.size());

        // 1) 이미지 저장
        if (imageUrls != null && !imageUrls.isEmpty()) {
            List<ItemImage> images = ItemImage.fromDto(item, imageUrls);
            log.debug("이미지 변환 완료: itemId={}, imagesToSave={}", item.getId(), images.size());

            itemImageRepository.saveAll(images);
            log.debug("이미지 저장 완료: itemId={}, savedImages={}", item.getId(), images.size());
        } else {
            log.debug("저장할 이미지 없음: itemId={}", item.getId());
        }

        // 2) 카테고리 매핑
        if (categoryIds != null && !categoryIds.isEmpty()) {
            List<Category> categories = categoryService.getValidCategories(categoryIds);
            List<Long> categoryIdList = categories.stream()
                    .map(Category::getId)
                    .toList();
            log.debug("유효 카테고리 조회 완료: itemId={}, categories={}", item.getId(), categoryIdList);

            List<ItemCategory> mappings = ItemCategory.createMappings(item, categories);
            log.debug("ItemCategory 매핑 생성 완료: itemId={}, mappingsCount={}", item.getId(), mappings.size());

            itemCategoryRepository.saveAll(mappings);
            log.debug("카테고리 매핑 저장 완료: itemId={}, savedMappings={}", item.getId(), mappings.size());
        } else {
            log.debug("매핑할 카테고리 없음: itemId={}", item.getId());
        }

        log.debug("saveItemDetails 완료: itemId={}", item.getId());
    }


    // 물품 조회
    public List<ItemResponseDto> getItemByItemType(String itemType) {
        List<? extends Item> items;

        if ("PRODUCT".equalsIgnoreCase(itemType)) {
            items = productRepository.findAll();
        } else if ("AUCTION".equalsIgnoreCase(itemType)) {
            items = auctionRepository.findAll();
        } else {
            throw new IllegalArgumentException("Unknown item type");
        }

        return items.stream()
                .map(item -> new ItemResponseDto(
                        item.getId(),
                        item.getName(),
                        item.getItemType(), // now works via @Transient
                        item.getDescription()
                ))
                .collect(Collectors.toList());
    }
}


