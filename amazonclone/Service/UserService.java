package com.example.amazonclone.Service;

import com.example.amazonclone.Model.MerchantStock;
import com.example.amazonclone.Model.Product;
import com.example.amazonclone.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MerchantStockService merchantStock;
    private final ProductService product;
    private final ProductService productService;
    ArrayList<User> users =new ArrayList<>();

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        user.setRewardPoints(0);
        users.add(user);
    }


    //1-extra endpoints-display how much the RewardPoints for a user
public int getRewardPoints (String userId){
        for(User u:users){
            if (u.getUserId().equalsIgnoreCase(userId)){
                return u.getRewardPoints();
            }
        }return 0;
}
    //1-extra endpoints-use the RewardPoints
public int useRewardPoints (String userId, int pointToUse){
        for (User u:users){
            if (u.getUserId().equalsIgnoreCase(userId)){
                if (u.getRewardPoints()<=pointToUse){
                    return 1;
                }u.setRewardPoints(getRewardPoints(userId)-pointToUse);
                double discount = pointToUse*0.1  ;
                u.setBalance(u.getBalance()+discount);
                return 2;
            }
        }return 3;
}

    public boolean updateUser (String userId , User user){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equalsIgnoreCase(userId)){
                users.set(i,user);
                return true;
            }
        }return false;
    }

    public boolean deleteUser (String userId ){
        for(User u:users){
            if(u.getUserId().equalsIgnoreCase(userId)){
                users.remove(u);
                return true;
            }
        }return false;
    }

    public boolean rechargeBalance (String userId, int amount){
        for(User u:users){
            if (u.getUserId().equalsIgnoreCase(userId)) {
                u.setBalance(u.getBalance()+amount);
                return true;
            }
        }return false;
    }
//12-Create endpoint where user can buy a product directly

    public int buyProduct(String userId,String productId , String merchantId ){
        for (User u :users){
    if(u.getUserId().equalsIgnoreCase(userId)){
        if (merchantStock.getMerchantStockByProductId_merchantId(productId,merchantId)==null){
return 1;
        }
        if(merchantStock.getStockBy_productId(productId)<=0){
            return 2;
        }
        if(u.getBalance()<product.getPriceByproductId(productId)){
            return 3;
        }
        merchantStock.reduceStock(productId,merchantId,1);
        u.setBalance(u.getBalance()-product.getPriceByproductId(productId));
        int point= (int) (product.getPriceByproductId(productId) /10);
        u.setRewardPoints(u.getRewardPoints()+ point);
        return 4;
    }
}return 5;
    }



//2-extra endpoints-display wishlist
public ArrayList<Product> displayWishList (String userId){
        for (User u :users){
            if (u.getUserId().equalsIgnoreCase(userId)){
                return u.getWishlist();
            }
        }return null;
}
//2-extra endpoints-add product to the wishlist
public int addToWishList (String userId , String productId){
        for(User u :users){
            if(u.getUserId().equalsIgnoreCase(userId)){
                for(Product p:productService.getProducts()){
                    if (product.getProductID(productId)){
                        if(u.getWishlist()==null){
                            u.setWishlist(new ArrayList<>());}
                        u.getWishlist().add(p);
                        return 1;
                    }
                     return 2;}
        }
    } return 3;
}

//3-extra endpoints-returnProduct
public int returnProduct (String userId , String productId){
        for (User u:users){
            if (u.getUserId().equalsIgnoreCase(userId)){
                if(!productService.getProductID(productId)){
                    return 1;
                }
                merchantStock.setStock(productId);
                u.setBalance(u.getBalance()+productService.getPriceByproductId(productId));
                return 2;
            }
        }return 3;

}



}
