package ir.altaytech.saeedmobile.Model;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Altay on 2/7/2018.
 */
@Keep
public class AmazingProduct {
    @SerializedName("Id")
    private int Id;
    @SerializedName("Price")
    private int Price;
    @SerializedName("IsAvailable")
    private Boolean IsAvailable;
    @SerializedName("DiscountPercent")
    private int DiscountPercent;
    @SerializedName("FinalPrice")
    private int FinalPrice;
    @SerializedName("PicPath")
    private String PicPath;
    @SerializedName("ProductId")
    private int ProductId;
    @SerializedName("ProductName")
    private String ProductName;
    @SerializedName("Description")
    private String Description;
    @SerializedName("Details")
    private String Details;
    @SerializedName("ShortName")
    private String ShortName;
    @SerializedName("GuaranteeDescription")
    private String GuaranteeDescription;

    public AmazingProduct(int id, int price, Boolean isAvailable, int discountPercent, int finalPrice, String picPath, int productId, String productName, String description, String details, String shortName, String guaranteeDescription) {
        Id = id;
        Price = price;
        IsAvailable = isAvailable;
        DiscountPercent = discountPercent;
        FinalPrice = finalPrice;
        PicPath = picPath;
        ProductId = productId;
        ProductName = productName;
        Description = description;
        Details = details;
        ShortName = shortName;
        GuaranteeDescription = guaranteeDescription;
    }

    public int getId() {
        return Id;
    }

    public int getPrice() {
        return Price;
    }

    public Boolean getAvailable() {
        return IsAvailable;
    }

    public int getDiscountPercent() {
        return DiscountPercent;
    }

    public int getFinalPrice() {
        return FinalPrice;
    }

    public String getPicPath() {
        return PicPath;
    }

    public int getProductId() {
        return ProductId;
    }

    public String getProductName() {
        return ProductName;
    }

    public String getDescription() {
        return Description;
    }

    public String getDetails() {
        return Details;
    }

    public String getShortName() {
        return ShortName;
    }

    public String getGuaranteeDescription() {
        return GuaranteeDescription;
    }
}