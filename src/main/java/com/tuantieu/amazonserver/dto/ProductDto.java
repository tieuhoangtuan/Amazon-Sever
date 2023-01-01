package com.tuantieu.amazonserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDto {
    private Long id;

    private String name;

    private Long categoryId;

    private Double price;

    private Long quantity;

    private String description;

    public ProductDto(String name, Long categoryId, Double price, Long quantity, String description) {
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }
}
