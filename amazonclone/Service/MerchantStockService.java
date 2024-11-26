package com.example.amazonclone.Service;

import com.example.amazonclone.Model.MerchantStock;
import com.example.amazonclone.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantStockService {
    ArrayList<MerchantStock> merchantStocks =new ArrayList<>();
private final MerchantService merchantService;
private final ProductService productService;

    public ArrayList<MerchantStock> getMerchantStock() {
        return merchantStocks;
    }

    public int addMerchantStock(MerchantStock merchantStock) {
        if (!merchantService.getMerchantID(merchantStock.getMerchantId())){
            return 1;
        }
        if (!productService.getProductID(merchantStock.getProductId())){
            return 2;
        }

        merchantStocks.add(merchantStock);
        return 3;
    }



    public boolean updateMerchantStock (String stockId , MerchantStock merchantStock){
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getStockId().equalsIgnoreCase(stockId)){
                merchantStocks.set(i,merchantStock);
                return true;
            }
        }return false;
    }

    public boolean deleteMerchantStock (String stockId ){
        for(MerchantStock m:merchantStocks){
            if(m.getStockId().equalsIgnoreCase(stockId)){
                merchantStocks.remove(m);
                return true;
            }
        }return false;
    }
    public int reduceStock (String productId ,String merchantId ,double amount){
        for(MerchantStock m:merchantStocks){
            if (m.getProductId().equalsIgnoreCase(productId)){
                if (!m.getMerchantId().equalsIgnoreCase(merchantId)){
                    return 1;}
                else if (m.getStock()>=amount){
                    m.setStock(m.getStock()-amount);
                    return 2;}
            }
        }return 3;
    }

    public int changStock (String productId ,String merchantId ,double amount){
        for(MerchantStock m:merchantStocks){
            if (m.getProductId().equalsIgnoreCase(productId)){
                if (!m.getMerchantId().equalsIgnoreCase(merchantId)){
                    return 1;}
                else {
                    m.setStock(m.getStock()+amount);
                    return 2;}
            }
        }return 3;
    }

public double getStockBy_productId (String productId){
        for (MerchantStock m:merchantStocks){
            if (m.getProductId().equalsIgnoreCase(productId)){
                return m.getStock();
            }
        }return 0;

}

    public void setStock (String productId){
        for(MerchantStock m:merchantStocks){
            if (m.getProductId().equalsIgnoreCase(productId)){
                double currentStock= m.getStock();
                if(currentStock>=0){
          m.setStock(m.getStock()+1);}
            }
        }
    }
//1-bounce endpoint
public  MerchantStock getMerchantStockByProductId_merchantId ( String productId ,String merchantId){
        for (MerchantStock m:merchantStocks){
            if (m.getProductId().equalsIgnoreCase(productId) && m.getMerchantId().equalsIgnoreCase(merchantId)){
                return m;
            }
        }return null;
}

    //5-extra endpoints-display the products with low stock for a specific merchant.
public ArrayList<MerchantStock> lowStock (String merchantId ) {
    ArrayList<MerchantStock> lowStock = new ArrayList<>();
    for (MerchantStock m : merchantStocks) {
        if (m.getMerchantId().equalsIgnoreCase(merchantId)) {
            if (m.getStock() <= 3) {
                lowStock.add(m);
            }
        }
    }        return lowStock;

}



}
