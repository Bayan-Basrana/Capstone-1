package com.example.amazonclone.Service;

import com.example.amazonclone.Model.Category;
import com.example.amazonclone.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {
    ArrayList<Category> categories =new ArrayList<>();

    public ArrayList<Category> getCategory() {
        return categories;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }



    public boolean updateCategory (String categoryId , Category category){
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getCategoryId().equalsIgnoreCase(categoryId)){
                categories.set(i,category);
                return true;
            }
        }return false;
    }

    public boolean deleteCategory (String categoryId ){
        for(Category c:categories){
            if(c.getCategoryId().equalsIgnoreCase(categoryId)){
                categories.remove(c);
                return true;
            }
        }return false;
    }

public boolean getCategoryID (String categoryId ){
        for (Category c:categories){
            if (c.getCategoryId().equalsIgnoreCase(categoryId)){
                return true;
            }
        }return false;
}










}
