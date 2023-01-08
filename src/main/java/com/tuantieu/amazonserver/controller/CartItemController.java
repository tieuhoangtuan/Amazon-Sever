package com.tuantieu.amazonserver.controller;

import com.tuantieu.amazonserver.dto.AddToCartDto;
import com.tuantieu.amazonserver.dto.ResponseObject;
import com.tuantieu.amazonserver.service.impl.CartItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/amazon/cart")
public class CartItemController {
    private final CartItemServiceImpl cartItemServiceImpl;

    @Autowired
    public CartItemController(CartItemServiceImpl cartItemServiceImpl) {
        this.cartItemServiceImpl = cartItemServiceImpl;
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    ResponseEntity<ResponseObject> getCartItemList(Long userId){
        return cartItemServiceImpl.getCartItemList(userId);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> addToCart(@RequestBody AddToCartDto addToCartDto, Long userId){
        return cartItemServiceImpl.addToCart(addToCartDto, userId);
    }
}
