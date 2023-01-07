package com.tuantieu.amazonserver.service;

import com.tuantieu.amazonserver.dto.ProductDto;
import com.tuantieu.amazonserver.entity.Product;
import com.tuantieu.amazonserver.dto.ResponseObject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProduct();

    ResponseEntity<ResponseObject> getProductById(Long id);

    ResponseEntity<ResponseObject> insertProduct(ProductDto newProductDto);

    ResponseEntity<ResponseObject> upsertProduct(Product newProduct, Long id);

    ResponseEntity<ResponseObject> deleteProduct(Long id);
}
