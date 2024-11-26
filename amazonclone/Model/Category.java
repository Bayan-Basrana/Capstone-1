package com.example.amazonclone.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {
    @NotEmpty(message = "ID cannot be empty")
    private String categoryId ;
    @NotEmpty(message = "name cannot be empty")
@Size(min = 3,message = "name must be more than 3")
    private String name ;


}
