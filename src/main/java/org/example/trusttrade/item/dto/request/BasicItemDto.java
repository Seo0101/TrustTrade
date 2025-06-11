package org.example.trusttrade.item.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicItemDto {

    // 사진
    @Size(max = 5, message = "이미지는 최대 5장까지 첨부할 수 있습니다.")
    private List<String> images;

    // 상품
    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private UUID sellerId;

    // 카테고리
    @Size(max = 3, message = "카테고리는 최대 3개까지 선택할 수 있습니다.")
    private List<Integer> categoryIds;

    // 가격
    @NotNull
    private Integer price;

    // 지도 위치
    @NotNull
    private String address;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;

}
