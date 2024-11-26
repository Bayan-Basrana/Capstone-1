package com.example.amazonclone.Model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {
    @NotEmpty(message = "ID cannot be empty")
    private String stockId ;
    @NotEmpty(message = "product ID cannot be empty")
    private String productId ;
    @NotEmpty(message = "merchant ID cannot be empty")
    private String merchantId ;
    @NotNull(message = "stock cannot be empty")
    @Max(value = 10,message = "stock must be 10 or more ")
    private double stock;

}
