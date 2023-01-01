package com.tuantieu.amazonserver.service.impl;

import com.tuantieu.amazonserver.dto.ProductDto;
import com.tuantieu.amazonserver.entity.Category;
import com.tuantieu.amazonserver.entity.Product;
import com.tuantieu.amazonserver.entity.ResponseObject;
import com.tuantieu.amazonserver.repository.CategoryRepository;
import com.tuantieu.amazonserver.repository.ProductRepository;
import com.tuantieu.amazonserver.service.ProductService;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private  ModelMapper modelMapper;


    @Override
    public List<ProductDto> getAllProduct(){
        return productRepository.findAll().stream().map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<ResponseObject> getProductById(Long id){
        Optional<ProductDto> foundProduct = productRepository.findById(id).map(product -> modelMapper.map(product,ProductDto.class));
        if(foundProduct.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","Query product successfully",foundProduct)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Cannot find product with id = " + id, "")
            );
        }

    }

    @Override
    public ResponseEntity<ResponseObject> insertProduct(@NotNull ProductDto newProductDto){
        Product foundProduct = productRepository.findProductByName(newProductDto.getName());
        Category category = categoryRepository.findOneById(newProductDto.getCategoryId());


//        if(category == null){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject("failed", "Category does not existed", "")
//            );
//        }

        if(foundProduct != null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new ResponseObject("failed", "Product name already existed", "")
            );
        }else {
            Product product = new Product();
//            product.setId(newProductDto.getId());
            product.setName(newProductDto.getName());


            product.setCategory(category);

            product.setPrice(newProductDto.getPrice());
            product.setQuantity(newProductDto.getQuantity());
            product.setDescription(newProductDto.getDescription());

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Insert product successfully", productRepository.save(product))
            );
        }
    }

    public ResponseEntity<ResponseObject> upsertProduct(Product newProduct, Long id){
        Product updateProduct = productRepository.findById(id).map(product -> {
            product.setName(newProduct.getName());
            product.setCategory(newProduct.getCategory());
            product.setPrice(newProduct.getPrice());
            product.setQuantity(newProduct.getQuantity());
            product.setDescription(newProduct.getDescription());
            return productRepository.save(product);
        }).orElseGet(() -> {
            newProduct.setId(id);
            return productRepository.save(newProduct);
        });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update product successfully", updateProduct)
        );
    }

    public ResponseEntity<ResponseObject> deleteProduct(Long id){
        boolean existProduct = productRepository.existsById(id);
        if(existProduct){
            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete product successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Can not find product to delete", "")
        );
    }
}
