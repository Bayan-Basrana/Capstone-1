package com.example.amazonclone.Controller;

import com.example.amazonclone.ApiResponse.ApiResponse;
import com.example.amazonclone.Model.Category;
import com.example.amazonclone.Model.Product;
import com.example.amazonclone.Service.CategoryService;
import com.example.amazonclone.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/getCategory")
    public ResponseEntity getCategory (){
        ArrayList<Category> categories= categoryService.getCategory();
        return ResponseEntity.status(200).body(categories);
    }

    @PostMapping("/addCategory")
    public ResponseEntity addCategory (@RequestBody @Valid Category category , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body(new ApiResponse("category added successfully"));
    }

    @PutMapping("/updateCategory/{categoryId}")
    public ResponseEntity updateCategory (@PathVariable String categoryId , @RequestBody @Valid Category category , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isUpdated = categoryService.updateCategory(categoryId,category);
        if (isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("category updated successfully"));
        }else return  ResponseEntity.status(400).body(new ApiResponse("category ID not found"));
    }

    @DeleteMapping("/deleteCategory/{categoryId}")
    public ResponseEntity deleteCategory (@PathVariable String categoryId ){
        boolean isDeleted = categoryService.deleteCategory(categoryId);
        if (isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("category deleted successfully"));
        }return ResponseEntity.status(400).body(new ApiResponse("category ID not found"));
    }

















}
