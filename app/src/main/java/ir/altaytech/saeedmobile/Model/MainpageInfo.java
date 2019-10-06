package ir.altaytech.saeedmobile.Model;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.util.List;
@Keep
public class MainpageInfo {

    @SerializedName("MobileNo")
    private String MobileNo;

    @SerializedName("IsUserLogined")
    private Boolean IsUserLogined;

    @SerializedName("CustomerFullName")
    private String CustomerFullName;

    @SerializedName("AmazingOffers")
    private List<AmazingProduct> AmazingOffers;

    @SerializedName("Sliders")
    private List<Slider> Sliders;

    @SerializedName("PromoSectionProducts")
    private List<PromoSectionProduct> PromoSectionProducts;

    @SerializedName("Groups")
    private List<Group> Groups;

    @SerializedName("Brands")
    private List<Brand> Brands;

    @SerializedName("SuggestedProducts")
    private List<Product> SuggestedProducts;


    @SerializedName("BaseInfos")
    private BaseInfos BaseInfos;

    public MainpageInfo(String mobileNo, Boolean isUserLogined, String customerFullName, List<AmazingProduct> amazingOffers, List<Slider> sliders, List<PromoSectionProduct> promoSectionProducts, List<Group> groups, List<Brand> brands, List<Product> suggestedProducts, BaseInfos baseInfos) {
        MobileNo = mobileNo;
        IsUserLogined = isUserLogined;
        CustomerFullName = customerFullName;
        AmazingOffers = amazingOffers;
        Sliders = sliders;
        PromoSectionProducts = promoSectionProducts;
        Groups = groups;
        Brands = brands;
        SuggestedProducts = suggestedProducts;
        BaseInfos = baseInfos;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public Boolean getUserLogined() {
        return IsUserLogined;
    }

    public String getCustomerFullName() {
        return CustomerFullName;
    }

    public List<AmazingProduct> getAmazingOffers() {
        return AmazingOffers;
    }

    public List<Slider> getSliders() {
        return Sliders;
    }

    public List<PromoSectionProduct> getPromoSectionProducts() {
        return PromoSectionProducts;
    }

    public List<Group> getGroups() {
        return Groups;
    }

    public List<Brand> getBrands() {
        return Brands;
    }

    public List<Product> getSuggestedProducts() {
        return SuggestedProducts;
    }

    public BaseInfos getBaseInfos() {
        return BaseInfos;
    }
}
