package com.tuantieu.amazonserver;

import com.tuantieu.amazonserver.entity.Category;
import com.tuantieu.amazonserver.repository.CategoryRepository;
import com.tuantieu.amazonserver.security.userprinciple.UserPrinciple;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.List;

public class categoryConfig {
    @Bean
    CommandLineRunner commandLineRunner(CategoryRepository categoryRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Category category1 = new Category(1L, "an lien");


                categoryRepository.save(category1);
            }
        };
    }
}
