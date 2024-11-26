package com.example.amazonclone.Controller;

import com.example.amazonclone.ApiResponse.ApiResponse;
import com.example.amazonclone.Model.Product;
import com.example.amazonclone.Model.User;
import com.example.amazonclone.Service.ProductService;
import com.example.amazonclone.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/getUser")
    public ResponseEntity getUser (){
        ArrayList<User> users= userService.getUsers();
        return ResponseEntity.status(200).body(users);
    }

    @PostMapping("/addUser")
    public ResponseEntity addUser (@RequestBody @Valid User user , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added successfully"));
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity updateUser (@PathVariable String id , @RequestBody @Valid User user , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isUpdated = userService.updateUser(id ,user);
        if (isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("User updated successfully"));
        }else return  ResponseEntity.status(400).body(new ApiResponse("User ID not found"));
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity deleteUser (@PathVariable String id ){
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully"));
        }return ResponseEntity.status(400).body(new ApiResponse("User ID not found"));
    }
//3-bounce endpoint
    @PutMapping("/rechargeBalance/{userId}/{amount}")
    public ResponseEntity rechargeBalance (@PathVariable String userId ,@PathVariable int amount ){
        boolean isRecharged = userService.rechargeBalance(userId, amount);
   if (isRecharged){
       return ResponseEntity.status(200).body(new ApiResponse("balance recharged successfully"));
   }else return ResponseEntity.status(400).body(new ApiResponse("user id not found"));
    }
//12-Create endpoint where user can buy a product directly
@PutMapping("/buyProduct/{id}/{productId}/{merchantId}")
    public ResponseEntity buyProduct (@PathVariable String id, @PathVariable String productId ,@PathVariable String merchantId ){
        int result = userService.buyProduct( id, productId ,  merchantId );
        if (result==1){
            return ResponseEntity.status(400).body("productId or merchantId noy found");
        }
        if(result==2){
            return ResponseEntity.status(400).body("product out of stock");
        }
        if(result==3){
            return ResponseEntity.status(400).body("Insufficient balance for user ID");
        }
        if(result==4){
            return ResponseEntity.status(200).body(" Purchase successful");
        }return ResponseEntity.status(400).body(new ApiResponse("user id not fond"));

    }
    //1-extra endpoints-display how much the RewardPoints for a user
    @GetMapping("/getRewardPoints/{userId}")
    public ResponseEntity getRewardPoints (@PathVariable String userId){
        if (userService.getRewardPoints(userId)!=0){
            return ResponseEntity.status(200).body(userService.getRewardPoints(userId));
        }else return ResponseEntity.status(400).body(new ApiResponse("yore don't have any RewardPoints  "));
    }
    //1-extra endpoints-use the RewardPoints
    @PutMapping("/useRewardPoints/{userId}/{pointToUse}")
    public ResponseEntity useRewardPoints (@PathVariable String userId, @PathVariable int pointToUse){
        int result = userService.useRewardPoints(userId,pointToUse);
        if (result==1){
            return ResponseEntity.status(400).body(new ApiResponse("no enough RewardPoints."));
        }
        if (result==2){
            return ResponseEntity.status(200).body(new ApiResponse("you used the RewardPoints successfully"));
        }else return ResponseEntity.status(400).body(new ApiResponse("user id not found"));
    }



//2-extra endpoints-display wishlist
    @GetMapping("/displayWishList/{userId}")
public ResponseEntity displayWishList (@PathVariable String userId){
        if (userService.displayWishList(userId)!=null){
            return ResponseEntity.status(200).body(userService.displayWishList(userId));
        }return ResponseEntity.status(400).body(new ApiResponse("user id not found"));
}


//2-extra endpoints-add product to the wishlist
@PutMapping("/addToWishList/{userId}/{productId}")
public ResponseEntity addToWishList (@PathVariable String userId ,@PathVariable String productId){
        int result = userService.addToWishList(userId, productId);
        if (result==1){
            return ResponseEntity.status(200).body(new ApiResponse("product added to wishlist successfully"));
        }
        if (result==2){
            return ResponseEntity.status(400).body(new ApiResponse("productId not found"));
        }return ResponseEntity.status(400).body(new ApiResponse("userId not found"));
}

//3-extra endpoints-returnProduct
@PutMapping("/return/{userId}/{productId}")
public ResponseEntity returnProduct (@PathVariable String userId ,@PathVariable String productId ){
    int result = userService.returnProduct(userId,productId);
    if (result==1){
        return ResponseEntity.status(400).body(new ApiResponse("productId not found"));
    }
    if (result==2){
        return ResponseEntity.status(200).body(new ApiResponse("product return successfully"));
    }return ResponseEntity.status(400).body(new ApiResponse("userId not found"));

}


}
