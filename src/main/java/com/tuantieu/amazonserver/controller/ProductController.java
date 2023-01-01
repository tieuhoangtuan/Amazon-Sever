package com.tuantieu.amazonserver.controller;

import com.tuantieu.amazonserver.dto.ProductDto;
import com.tuantieu.amazonserver.entity.Product;
import com.tuantieu.amazonserver.entity.ResponseObject;
import com.tuantieu.amazonserver.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/amazon/product")
public class ProductController {
    private final ProductServiceImpl productServiceImpl;

    @Autowired
    public ProductController(ProductServiceImpl productServiceImpl) { this.productServiceImpl = productServiceImpl; }

    @GetMapping("/")
    public List<ProductDto> getAllProduct(){
        return productServiceImpl.getAllProduct();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getProductById(@PathVariable Long id){
        return productServiceImpl.getProductById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseObject> insertProduct(@RequestBody ProductDto newProductDto){
        return productServiceImpl.insertProduct(newProductDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateProduct(@RequestBody Product product, @PathVariable Long id){
        return productServiceImpl.upsertProduct(product, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id){
        return productServiceImpl.deleteProduct(id);
    }
}
