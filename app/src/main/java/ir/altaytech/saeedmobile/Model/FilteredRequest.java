package ir.altaytech.saeedmobile.Model;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Altay on 2/7/2018.
 */
@Keep
public class FilteredRequest {
    @SerializedName("SubGroupId")
    private int SubGroupId;
    @SerializedName("TagId")
    private int TagId;
    @SerializedName("SearchValue")
    private String SearchValue;
    @SerializedName("OrderBy")
    private int OrderBy;
    @SerializedName("SearchInResult")
    private String SearchInResult;
    @SerializedName("FromPriceRange")
    private int FromPriceRange;
    @SerializedName("ToPriceRange")
    private int ToPriceRange;
    @SerializedName("IsAvalible")
    private Boolean IsAvalible;
    @SerializedName("Page")
    private int Page;

    public FilteredRequest(int subGroupId, int tagId, String searchValue, int orderBy, String searchInResult, int fromPriceRange, int toPriceRange, Boolean isAvalible, int page) {

        SubGroupId = subGroupId;
        TagId = tagId;
        SearchValue = searchValue;
        OrderBy = orderBy;
        SearchInResult = searchInResult;
        FromPriceRange = fromPriceRange;
        ToPriceRange = toPriceRange;
        IsAvalible = isAvalible;
        Page = page;
    }

    public FilteredRequest(String searchValue, int orderBy, int fromPriceRange, int toPriceRange) {
        SearchValue = searchValue;
        OrderBy = orderBy;
        FromPriceRange = fromPriceRange;
        ToPriceRange = toPriceRange;
    }

    public FilteredRequest(String searchValue, int orderBy, String searchInResult, int fromPriceRange, int toPriceRange, Boolean isAvalible, int page) {
        SearchValue = searchValue;
        OrderBy = orderBy;
        SearchInResult = searchInResult;
        FromPriceRange = fromPriceRange;
        ToPriceRange = toPriceRange;
        IsAvalible = isAvalible;
        Page = page;
    }

    public FilteredRequest(String searchValue, int orderBy) {
        SearchValue = searchValue;
        OrderBy = orderBy;
    }

    public FilteredRequest(String searchValue) {
        SearchValue = searchValue;
    }

    public int getSubGroupId() {
        return SubGroupId;
    }

    public int getTagId() {
        return TagId;
    }

    public String getSearchValue() {
        return SearchValue;
    }

    public int getOrderBy() {
        return OrderBy;
    }

    public String getSearchInResult() {
        return SearchInResult;
    }

    public int getFromPriceRange() {
        return FromPriceRange;
    }

    public int getToPriceRange() {
        return ToPriceRange;
    }

    public Boolean getAvalible() {
        return IsAvalible;
    }

    public int getPage() {
        return Page;
    }
}