package com.tuantieu.amazonserver.service;

import com.tuantieu.amazonserver.dto.AddToCartDto;
import com.tuantieu.amazonserver.dto.ResponseObject;
import com.tuantieu.amazonserver.entity.CartItem;
import org.springframework.http.ResponseEntity;

public interface CartItemService {
    ResponseEntity<?> addToCart(AddToCartDto addToCartDto, Long userId);

    ResponseEntity<ResponseObject> getCartItemList(Long userId);
}
