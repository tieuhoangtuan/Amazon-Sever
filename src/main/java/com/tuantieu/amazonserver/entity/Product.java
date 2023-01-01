package com.tuantieu.amazonserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.CascadeType.PERSIST;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(EntityListeners.class)
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private Double price;

    private Long quantity;

    private String description;

    public Product(String name, Category category, Double price, Long quantity, String description) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }
}
