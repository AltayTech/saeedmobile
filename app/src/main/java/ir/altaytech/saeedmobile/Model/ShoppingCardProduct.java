package ir.altaytech.saeedmobile.Model;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Altay on 2/7/2018.
 */
@Keep
public class ShoppingCardProduct {
    @SerializedName("ProductId")
    private int ProductId;
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
    @SerializedName("ProductPicPath")
    private String ProductPicPath;
    @SerializedName("Unit")
    private String Unit;
    @SerializedName("Weight")
    private int Weight;

    public ShoppingCardProduct(int productId, String productName, int price,
                               int discountPercent, int finalPrice, int quantity,
                               String productPicPath, String unit, int weight) {
        ProductId = productId;
        ProductName = productName;
        Price = price;
        DiscountPercent = discountPercent;
        FinalPrice = finalPrice;
        Quantity = quantity;
        ProductPicPath = productPicPath;
        Unit = unit;
        Weight = weight;
    }


    public int getProductId() {
        return ProductId;
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

    public String getProductPicPath() {
        return ProductPicPath;
    }

    public String getUnit() {
        return Unit;
    }

    public int getWeight() {
        return Weight;
    }
}