package ir.altaytech.saeedmobile.Model;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Altay on 2/7/2018.
 */
@Keep
public class FilteredResponse {
    @SerializedName("TotalCount")
    private int TotalCount;
    @SerializedName("Page")
    private int Page;
    @SerializedName("FilteredProducts")
    private List<Product> FilteredProducts;

    @SerializedName("FilteredBrands")
    private List<Brand> FilteredBrands;


    @SerializedName("FilteredPriceRange")
    private FilteredPriceRange FilteredPriceRange;

    public FilteredResponse(int totalCount, int page, List<Product> filteredProducts, List<Brand> filteredBrands, ir.altaytech.saeedmobile.Model.FilteredPriceRange filteredPriceRange) {
        TotalCount = totalCount;
        Page = page;
        FilteredProducts = filteredProducts;
        FilteredBrands = filteredBrands;
        FilteredPriceRange = filteredPriceRange;
    }

    public FilteredResponse(int totalCount, int page, List<Product> filteredProducts, List<Brand> filteredBrands) {
        TotalCount = totalCount;
        Page = page;
        FilteredProducts = filteredProducts;
        FilteredBrands = filteredBrands;
    }

    public int getTotalCount() {
        return TotalCount;
    }

    public int getPage() {
        return Page;
    }

    public List<Product> getFilteredProducts() {
        return FilteredProducts;
    }

    public List<Brand> getFilteredBrands() {
        return FilteredBrands;
    }

    public ir.altaytech.saeedmobile.Model.FilteredPriceRange getFilteredPriceRange() {
        return FilteredPriceRange;
    }
}