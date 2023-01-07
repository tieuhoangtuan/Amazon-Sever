package com.tuantieu.amazonserver.service.impl;

import com.tuantieu.amazonserver.entity.Category;
import com.tuantieu.amazonserver.dto.ResponseObject;
import com.tuantieu.amazonserver.repository.CategoryRepository;
import com.tuantieu.amazonserver.service.CategoryService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    @Override
    public ResponseEntity<ResponseObject> getCategoryById(Long id){
        Optional<Category> foundCategory = categoryRepository.findById(id);
        if(foundCategory.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Query category successfully", foundCategory)
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Can not find category with id: " + id)
            );
        }
    }

    @Override
    public ResponseEntity<ResponseObject> insertCategory(@NotNull Category newCategory){
        Category foundCategory = categoryRepository.findCategoryByName(newCategory.getName());
        if(foundCategory != null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Category name already existed", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert category successfully", categoryRepository.save(newCategory))
        );
    }

    @Override
    public ResponseEntity<ResponseObject> upsertCategory(Category newCategory, Long id){
        Category updateCategory = categoryRepository.findById(id).map(category -> {
            category.setName(newCategory.getName());
            return categoryRepository.save(category);
        }).orElseGet(() -> {
            newCategory.setId(id);
            return categoryRepository.save(newCategory);
        });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update category successfully", updateCategory)
        );
    }

    @Override
    public ResponseEntity<ResponseObject> deleteCategory(Long id){
        boolean existCategory = categoryRepository.existsById(id);
        if(existCategory){
            categoryRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete category successfully")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Can not find category to delete")
        );
    }
}
