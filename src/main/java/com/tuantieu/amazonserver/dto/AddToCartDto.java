package com.tuantieu.amazonserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddToCartDto {
    private Long id;
    private Long productId;
    private int quantity;

    public AddToCartDto(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
