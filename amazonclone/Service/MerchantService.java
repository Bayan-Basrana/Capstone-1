package com.example.amazonclone.Service;

import com.example.amazonclone.Model.Category;
import com.example.amazonclone.Model.Merchant;
import com.example.amazonclone.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantService {
    ArrayList<Merchant> merchants =new ArrayList<>();

    public ArrayList<Merchant> getMerchant() {
        return merchants;
    }

    public void addMerchant(Merchant merchant) {
        merchants.add(merchant);
    }



    public boolean updateMerchant (String merchantId , Merchant merchant){
        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getMerchantId().equalsIgnoreCase(merchantId)){
                merchants.set(i,merchant);
                return true;
            }
        }return false;
    }

    public boolean deleteMerchant (String merchantId ){
        for(Merchant m:merchants){
            if(m.getMerchantId().equalsIgnoreCase(merchantId)){
                merchants.remove(m);
                return true;
            }
        }return false;
    }

public  boolean getMerchantID (String merchantId ) {
    for (Merchant m : merchants) {
        if (m.getMerchantId().equalsIgnoreCase(merchantId)) {
            return true;
        }
    }
    return false;
}


}
