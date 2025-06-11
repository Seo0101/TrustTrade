package org.example.trusttrade.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemResponseDto {
    private Long id;
    private String name;
    private String itemType;
    private String description;

    public ItemResponseDto(Long id, String name, String itemType, String description) {
        this.id = id;
        this.name = name;
        this.itemType = itemType;
        this.description = description;
    }

}
