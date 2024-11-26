package com.example.amazonclone.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class User {

    @NotEmpty(message = "ID cannot be empty")
    private String userId ;
    @NotEmpty(message = "name cannot be empty")
    @Size(min = 5, message = "username must be more than 5")
    private String username ;
    @NotEmpty(message = "password cannot be empty")
    @Size(min = 7, message = "password must be more than 6")
//    @Pattern(regexp = "^[a-zA-Z]\\d{6}$",message = " password must have characters and digits only")
    private String password;
    @Email(message = "enter a valid email")
    @NotEmpty(message = "email cannot be empty")
    private String email;
    @NotEmpty(message = "role cannot be empty")
@Pattern(regexp = "Admin|Customer")
    private String role;
    @NotNull(message = "balance cannot be empty")
    @Positive(message = "balance must be Positive")
    private double balance;
    @PositiveOrZero(message = "rewardPoints must be Positive Or Zero ")
    private int rewardPoints=0;
 private ArrayList<Product> wishlist =new ArrayList<>();
}
