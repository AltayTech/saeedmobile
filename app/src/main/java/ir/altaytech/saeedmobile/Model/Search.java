package ir.altaytech.saeedmobile.Model;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Altay on 2/7/2018.
 */
@Keep
public class Search {
    @SerializedName("Tags")
    private List<Property> Tags;
    @SerializedName("SubGroups")
    private List<Property> SubGroups;
    @SerializedName("Products")
    private List<Property> Products;

    public Search(List<Property> tags, List<Property> subGroups, List<Property> products) {
        Tags = tags;
        SubGroups = subGroups;
        Products = products;
    }

    public List<Property> getTags() {
        return Tags;
    }

    public List<Property> getSubGroups() {
        return SubGroups;
    }

    public List<Property> getProducts() {
        return Products;
    }
}