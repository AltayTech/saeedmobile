package ir.altaytech.saeedmobile.Model;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Altay on 2/7/2018.
 */
@Keep
public class PromoSectionProduct implements Serializable {
    @SerializedName("Id")
    private int Id;
    @SerializedName("Title")
    private String Title;
    @SerializedName("PromProducts")
    private List<Product> PromProducts;

    public PromoSectionProduct(int id, String title, List<Product> promProducts) {
        Id = id;
        Title = title;
        PromProducts = promProducts;
    }

    public int getId() {
        return Id;
    }

    public String getTitle() {
        return Title;
    }

    public List<Product> getPromProducts() {
        return PromProducts;
    }
}