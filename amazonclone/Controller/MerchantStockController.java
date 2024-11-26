package com.example.amazonclone.Controller;

import com.example.amazonclone.ApiResponse.ApiResponse;
import com.example.amazonclone.Model.MerchantStock;
import com.example.amazonclone.Model.Product;
import com.example.amazonclone.Service.MerchantService;
import com.example.amazonclone.Service.MerchantStockService;
import com.example.amazonclone.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/merchantStock")
@RequiredArgsConstructor
public class MerchantStockController {
    private final MerchantStockService merchantStockService;

    @GetMapping("/getMerchantStock")
    public ResponseEntity getMerchantStock (){
        ArrayList<MerchantStock> merchantStocks= merchantStockService.getMerchantStock();
        return ResponseEntity.status(200).body(merchantStocks);
    }

    @PostMapping("/addMerchantStock")
    public ResponseEntity addMerchantStock (@RequestBody @Valid MerchantStock merchantStock , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        int result =merchantStockService.addMerchantStock(merchantStock);
if (result==1){
    return ResponseEntity.status(400).body(new ApiResponse("MerchantID not found"));
}
if (result==2){
    return ResponseEntity.status(400).body(new ApiResponse("ProductId not found"));
}
else
        return ResponseEntity.status(200).body(new ApiResponse("merchantStocks added successfully"));
    }

    @PutMapping("/updateMerchantStock/{stockId}")
    public ResponseEntity updateMerchantStock (@PathVariable String stockId , @RequestBody @Valid MerchantStock merchantStock , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isUpdated = merchantStockService.updateMerchantStock(stockId,merchantStock);
        if (isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("MerchantStock updated successfully"));
        }else return  ResponseEntity.status(400).body(new ApiResponse("MerchantStock ID not found"));
    }

    @DeleteMapping("/deleteMerchantStock/{stockId}")
    public ResponseEntity deleteMerchantStock (@PathVariable String stockId ){
        boolean isDeleted = merchantStockService.deleteMerchantStock(stockId);
        if (isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("MerchantStock deleted successfully"));
        }return ResponseEntity.status(400).body(new ApiResponse("MerchantStock ID not found"));
    }
//11- endpoint where merchant can add more stocks of product to a merchant Stock
@PutMapping("/changStock/{productId}/{merchantId}/{amount}")
    public ResponseEntity changStock (@PathVariable String productId ,@PathVariable String merchantId ,@PathVariable double amount){
        int result =merchantStockService.changStock(productId ,merchantId ,amount);
         if (result==1){
    return ResponseEntity.status(400).body(new ApiResponse("merchantId not found"));
      }
         if (result==2){
             return ResponseEntity.status(200).body(new ApiResponse(" chang Stock successfully"));
         }
    return ResponseEntity.status(400).body(new ApiResponse("productId not found"));
    }

    @GetMapping("/getStockByProductId_merchantId/{productId}/{merchantId}")
public ResponseEntity getMerchantStockByProductId_merchantId (@PathVariable String productId ,@PathVariable String merchantId ){
        if(merchantStockService.getMerchantStockByProductId_merchantId(productId,merchantId)!=null){
            return ResponseEntity.status(200).body(merchantStockService.getMerchantStockByProductId_merchantId(productId,merchantId));
        }return ResponseEntity.status(400).body("invalid IDs");
}

    //5-extra endpoints-display the products with low stock for a specific merchant.
@GetMapping("/lowStock/{merchantId}")
public ResponseEntity lowStock (@PathVariable String merchantId){
        ArrayList<MerchantStock> lowStock =merchantStockService.lowStock(merchantId);
        if (merchantStockService.lowStock(merchantId)!= null){
            return ResponseEntity.status(200).body(lowStock);
        }return ResponseEntity.status(400).body("there is no low stock product");
}


}
