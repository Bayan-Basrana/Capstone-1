package com.example.amazonclone.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    @NotEmpty(message = "ID cannot be empty")
    private String productId ;
    @NotEmpty(message = "name cannot be empty")
@Size(min = 3, message = "name must be more than 3")
    private String name ;
    @NotNull(message = "price cannot be empty")
    @Positive(message = "price must be positive")
    private double price ;
    @NotEmpty(message = "category ID cannot be empty")
    private String categoryID;


}
