package ir.altaytech.saeedmobile.Model;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Altay on 2/7/2018.
 */
@Keep
public class OrderDetail {
    @SerializedName("ProductName")
    private String ProductName;
    @SerializedName("Price")
    private int Price;
    @SerializedName("DiscountPercent")
    private int DiscountPercent;
    @SerializedName("FinalPrice")
    private int FinalPrice;
    @SerializedName("Quantity")
    private int Quantity;
    @SerializedName("ProductUnit")
    private String ProductUnit;

    public OrderDetail(String productName, int price, int discountPercent, int finalPrice, int quantity, String productUnit) {
        ProductName = productName;
        Price = price;
        DiscountPercent = discountPercent;
        FinalPrice = finalPrice;
        Quantity = quantity;
        ProductUnit = productUnit;
    }

    public String getProductName() {
        return ProductName;
    }

    public int getPrice() {
        return Price;
    }

    public int getDiscountPercent() {
        return DiscountPercent;
    }

    public int getFinalPrice() {
        return FinalPrice;
    }

    public int getQuantity() {
        return Quantity;
    }

    public String getProductUnit() {
        return ProductUnit;
    }
}