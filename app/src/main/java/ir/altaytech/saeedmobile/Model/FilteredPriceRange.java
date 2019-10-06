package ir.altaytech.saeedmobile.Model;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Altay on 2/7/2018.
 */
@Keep
public class FilteredPriceRange {
    @SerializedName("MinPrice")
    private int MinPrice;
    @SerializedName("MaxPrice")
    private int MaxPrice;

    public FilteredPriceRange(int minPrice, int maxPrice) {
        MinPrice = minPrice;
        MaxPrice = maxPrice;
    }


    public int getMinPrice() {
        return MinPrice;
    }

    public int getMaxPrice() {
        return MaxPrice;
    }
}