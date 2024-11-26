package com.example.amazonclone.Controller;

import com.example.amazonclone.ApiResponse.ApiResponse;
import com.example.amazonclone.Model.Merchant;
import com.example.amazonclone.Model.Product;
import com.example.amazonclone.Service.MerchantService;
import com.example.amazonclone.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @GetMapping("/getMerchant")
    public ResponseEntity getMerchant (){
        ArrayList<Merchant> merchants= merchantService.getMerchant();
        return ResponseEntity.status(200).body(merchants);
    }

    @PostMapping("/addMerchant")
    public ResponseEntity addMerchant (@RequestBody @Valid Merchant merchant , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        merchantService.addMerchant(merchant);
        return ResponseEntity.status(200).body(new ApiResponse("Merchant added successfully"));
    }

    @PutMapping("/updateMerchant/{merchantId}")
    public ResponseEntity updateMerchant (@PathVariable String merchantId , @RequestBody @Valid Merchant merchant , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isUpdated = merchantService.updateMerchant(merchantId,merchant);
        if (isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("Merchant updated successfully"));
        }else return  ResponseEntity.status(400).body(new ApiResponse("Merchant ID not found"));
    }

    @DeleteMapping("/deleteMerchant/{merchantId}")
    public ResponseEntity deleteMerchant (@PathVariable String merchantId ){
        boolean isDeleted = merchantService.deleteMerchant(merchantId);
        if (isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("Merchant deleted successfully"));
        }return ResponseEntity.status(400).body(new ApiResponse("Merchant ID not found"));
    }







}
