package ir.altaytech.saeedmobile.Model;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Altay on 2/7/2018.
 */
@Keep
public class BaseInfos {
    @SerializedName("TaxRate")
    private int TaxRate;
    @SerializedName("MinimumOrders")
    private int MinimumOrders;
    @SerializedName("FixedAreaDeliveryFee")
    private int FixedAreaDeliveryFee;
    @SerializedName("PreparationUnit")
    private String PreparationUnit;
    @SerializedName("BrandTitle")
    private String BrandTitle;
    @SerializedName("CityId")
    private int CityId;
    @SerializedName("MinimumOrder")
    private String MinimumOrder;
    @SerializedName("ShopName")
    private String ShopName;
    @SerializedName("Lat")
    private Double Lat;
    @SerializedName("Lng")
    private Double Lng;
    @SerializedName("Address")
    private String Address;
    @SerializedName("TellNo")
    private String TellNo;

    public BaseInfos(int taxRate, int minimumOrders, int fixedAreaDeliveryFee, String preparationUnit, String brandTitle, int cityId, String minimumOrder, String shopName, Double lat, Double lng, String address, String tellNo) {
        TaxRate = taxRate;
        MinimumOrders = minimumOrders;
        FixedAreaDeliveryFee = fixedAreaDeliveryFee;
        PreparationUnit = preparationUnit;
        BrandTitle = brandTitle;
        CityId = cityId;
        MinimumOrder = minimumOrder;
        ShopName = shopName;
        Lat = lat;
        Lng = lng;
        Address = address;
        TellNo = tellNo;
    }

    public int getTaxRate() {
        return TaxRate;
    }

    public int getMinimumOrders() {
        return MinimumOrders;
    }

    public int getFixedAreaDeliveryFee() {
        return FixedAreaDeliveryFee;
    }

    public String getPreparationUnit() {
        return PreparationUnit;
    }

    public String getBrandTitle() {
        return BrandTitle;
    }

    public int getCityId() {
        return CityId;
    }

    public String getMinimumOrder() {
        return MinimumOrder;
    }

    public String getShopName() {
        return ShopName;
    }

    public Double getLat() {
        return Lat;
    }

    public Double getLng() {
        return Lng;
    }

    public String getAddress() {
        return Address;
    }

    public String getTellNo() {
        return TellNo;
    }
}