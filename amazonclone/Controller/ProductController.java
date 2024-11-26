package com.example.amazonclone.Controller;

import com.example.amazonclone.ApiResponse.ApiResponse;
import com.example.amazonclone.Model.Product;
import com.example.amazonclone.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/getProduct")
    public ResponseEntity getProduct (){
        ArrayList<Product> products= productService.getProducts();
        return ResponseEntity.status(200).body(products);
    }

    @PostMapping("/addProduct")
    public ResponseEntity addProduct (@RequestBody @Valid Product product , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isAdded=productService.addProducts(product);
        if (isAdded){
            return ResponseEntity.status(200).body(new ApiResponse("Product added successfully"));

        }
        else
            return ResponseEntity.status(400).body("category id not found");
    }

    @PutMapping("/updateProduct/{productId}")
    public ResponseEntity updateProduct (@PathVariable String productId , @RequestBody @Valid Product product , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isUpdated = productService.updateProduct(productId ,product);
        if (isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("Product updated successfully"));
        }else return  ResponseEntity.status(400).body(new ApiResponse("Product ID not found"));
    }

@DeleteMapping("/deleteProduct/{productId}")
public ResponseEntity deleteProduct (@PathVariable String productId ){
        boolean isDeleted = productService.deleteProduct(productId);
        if (isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("Product deleted successfully"));
        }return ResponseEntity.status(400).body(new ApiResponse("Product ID not found"));
}

@GetMapping("/productByCategory/{categoryID}")
public  ResponseEntity getProductByCategory (@PathVariable String categoryID){
        ArrayList<Product> productByCategory =productService.getProductByCategory(categoryID);
        if (productService.getProductByCategory(categoryID)!=null){
            return ResponseEntity.status(200).body(productByCategory);
        }return ResponseEntity.status(400).body(new ApiResponse("no product by this category "));
}
    //4-extra endpoints-Compere product
@GetMapping("/productCompere")
public ResponseEntity productCompere (@RequestBody String[] productIds){
        ArrayList<Product> productCompere =productService.productCompere(productIds);
        if (productService.productCompere(productIds)!=null){
            return ResponseEntity.status(200).body(productCompere);
        }return ResponseEntity.status(400).body(new ApiResponse("no product by this IDs "));
}

}
