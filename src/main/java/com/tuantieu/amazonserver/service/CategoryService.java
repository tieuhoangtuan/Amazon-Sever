package com.tuantieu.amazonserver.service;


import com.tuantieu.amazonserver.entity.Category;
import com.tuantieu.amazonserver.entity.ResponseObject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory();

    ResponseEntity<ResponseObject> getCategoryById(Long id);

    ResponseEntity<ResponseObject> insertCategory(Category newCategory);

    ResponseEntity<ResponseObject> upsertCategory(Category newCategory, Long id);

    ResponseEntity<ResponseObject> deleteCategory(Long id);
}
