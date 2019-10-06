package ir.altaytech.saeedmobile.Model;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ir.altaytech.saeedmobile.Model.subclass.HighlightedProductPropertyValue;
import ir.altaytech.saeedmobile.Model.subclass.ProductPic;
import ir.altaytech.saeedmobile.Model.subclass.ProductPropertyGroup;
import ir.altaytech.saeedmobile.Model.subclass.ProductPropertyValue;

/**
 * Created by Altay on 2/7/2018.
 */
@Keep
public class Product {
    @SerializedName("Id")
    private int Id;
    @SerializedName("Description")
    private String Description;
    @SerializedName("BrandTitle")
    private String BrandTitle;
    @SerializedName("GuaranteeDescription")
    private String GuaranteeDescription;
    @SerializedName("SubGroupTitle")
    private String SubGroupTitle;
    @SerializedName("GroupTitle")
    private String GroupTitle;
    @SerializedName("Unit")
    private String Unit;
    @SerializedName("Name")
    private String Name;
    @SerializedName("ShortName")
    private String ShortName;
    @SerializedName("DiscountPercent")
    private int DiscountPercent;
    @SerializedName("IsAvailable")
    private Boolean IsAvailable;
    @SerializedName("Price")
    private int Price;
    @SerializedName("FinalPrice")
    private int FinalPrice;
    @SerializedName("PicPath")
    private String PicPath;
    @SerializedName("ProductPics")
    private List<ProductPic> ProductPics;
    @SerializedName("HighlightedProductPropertyValue")
    private List<HighlightedProductPropertyValue> HighlightedProductPropertyValue;
    @SerializedName("ProductPropertyGroups")
    private List<ProductPropertyGroup> ProductPropertyGroups;
    @SerializedName("ProductPropertyValue")
    private List<ProductPropertyValue> ProductPropertyValue;

    public Product(int id, String description, String brandTitle, String guaranteeDescription, String subGroupTitle, String groupTitle, String unit, String name, String shortName, int discountPercent, Boolean isAvailable, int price, int finalPrice, String picPath, List<ProductPic> productPics, List<ir.altaytech.saeedmobile.Model.subclass.HighlightedProductPropertyValue> highlightedProductPropertyValue, List<ProductPropertyGroup> productPropertyGroups, List<ir.altaytech.saeedmobile.Model.subclass.ProductPropertyValue> productPropertyValue) {
        Id = id;
        Description = description;
        BrandTitle = brandTitle;
        GuaranteeDescription = guaranteeDescription;
        SubGroupTitle = subGroupTitle;
        GroupTitle = groupTitle;
        Unit = unit;
        Name = name;
        ShortName = shortName;
        DiscountPercent = discountPercent;
        IsAvailable = isAvailable;
        Price = price;
        FinalPrice = finalPrice;
        PicPath = picPath;
        ProductPics = productPics;
        HighlightedProductPropertyValue = highlightedProductPropertyValue;
        ProductPropertyGroups = productPropertyGroups;
        ProductPropertyValue = productPropertyValue;
    }

    public Product(int id, String name, String shortName, int discountPercent, int price, int finalPrice, String picPath) {
        Id = id;
        Name = name;
        ShortName = shortName;
        DiscountPercent = discountPercent;
        Price = price;
        FinalPrice = finalPrice;
        PicPath = picPath;
    }

    public Product(int id, String shortName, String description, int price, String picPath) {
        Id = id;
        Description = description;
        ShortName = shortName;
        Price = price;
        PicPath = picPath;
    }

    public int getId() {
        return Id;
    }

    public String getDescription() {
        return Description;
    }

    public String getBrandTitle() {
        return BrandTitle;
    }

    public String getGuaranteeDescription() {
        return GuaranteeDescription;
    }

    public String getSubGroupTitle() {
        return SubGroupTitle;
    }

    public String getGroupTitle() {
        return GroupTitle;
    }

    public String getUnit() {
        return Unit;
    }

    public String getName() {
        return Name;
    }

    public String getShortName() {
        return ShortName;
    }

    public int getDiscountPercent() {
        return DiscountPercent;
    }

    public Boolean getAvailable() {
        return IsAvailable;
    }

    public int getPrice() {
        return Price;
    }

    public int getFinalPrice() {
        return FinalPrice;
    }

    public String getPicPath() {
        return PicPath;
    }

    public List<ProductPic> getProductPics() {
        return ProductPics;
    }

    public List<ir.altaytech.saeedmobile.Model.subclass.HighlightedProductPropertyValue> getHighlightedProductPropertyValue() {
        return HighlightedProductPropertyValue;
    }

    public List<ProductPropertyGroup> getProductPropertyGroups() {
        return ProductPropertyGroups;
    }

    public List<ir.altaytech.saeedmobile.Model.subclass.ProductPropertyValue> getProductPropertyValue() {
        return ProductPropertyValue;
    }
}