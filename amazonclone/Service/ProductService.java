package com.example.amazonclone.Service;

import com.example.amazonclone.Model.Merchant;
import com.example.amazonclone.Model.Product;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProductService {
    ArrayList<Product> products =new ArrayList<>();
    private final CategoryService categoryService;

    public ArrayList<Product> getProducts() {
        return products;
    }

    public boolean addProducts(Product product) {
        if(categoryService.getCategoryID(product.getCategoryID())){
            products.add(product);
            return true;
        }
         return false;
    }



    public boolean updateProduct (String productId , Product product){
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId().equalsIgnoreCase(productId)){
                products.set(i,product);
                return true;
            }
        }return false;
    }

    public boolean deleteProduct (String productId ){
        for(Product p:products){
            if(p.getProductId().equalsIgnoreCase(productId)){
                products.remove(p);
                return true;
            }
        }return false;
    }

    public  boolean getProductID (String productId ) {
        for (Product p : products) {
            if (p.getProductId().equalsIgnoreCase(productId)) {
                return true;
            }
        }
        return false;
    }


    public double getPriceByproductId(String productId) {
        for (Product p:products){
            if(p.getProductId().equalsIgnoreCase(productId)){
                return p.getPrice();
            }
        }
        return 0;
    }


    public ArrayList<Product> getProductByCategory (String categoryID){
        ArrayList<Product> productByCategory =new ArrayList<>();
        for(Product p:products){
            if (p.getCategoryID().equalsIgnoreCase(categoryID)){
                productByCategory.add(p);
            }return productByCategory;
        }return null;

    }

    //4-extra endpoints-Compere product
    public ArrayList<Product> productCompere(String[] productIds) {
        ArrayList<Product> productCompere = new ArrayList<>();
        for (Product p : products) {
            for (String s : productIds) {
                if (s.equalsIgnoreCase(p.getProductId())) {
                    productCompere.add(p);
                }

            }
        }return productCompere;



    }





}
