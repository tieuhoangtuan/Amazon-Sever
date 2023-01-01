package com.tuantieu.amazonserver.controller;

import com.tuantieu.amazonserver.entity.Category;
import com.tuantieu.amazonserver.entity.Product;
import com.tuantieu.amazonserver.entity.ResponseObject;
import com.tuantieu.amazonserver.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/amazon/category")
public class CategoryController {
    private final CategoryServiceImpl categoryServiceImpl;

    @Autowired
    public CategoryController(CategoryServiceImpl categoryServiceImpl) { this.categoryServiceImpl = categoryServiceImpl; }

    @GetMapping
    public List<Category> getAllCategory(){
        return categoryServiceImpl.getAllCategory();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getCategoryById(@PathVariable Long id){
        return categoryServiceImpl.getCategoryById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseObject> insertCategory(@RequestBody Category category){
        return categoryServiceImpl.insertCategory(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateCategory(@RequestBody Category category, @PathVariable Long id){
        return categoryServiceImpl.upsertCategory(category, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteCategory(@PathVariable Long id){
        return categoryServiceImpl.deleteCategory(id);
    }
}
