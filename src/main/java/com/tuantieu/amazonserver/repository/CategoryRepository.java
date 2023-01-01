package com.tuantieu.amazonserver.repository;

import com.tuantieu.amazonserver.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryByName(String name);

    Category findOneById(Long id);
}
