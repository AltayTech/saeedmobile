package ir.altaytech.saeedmobile.Model.other;



import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by SCIT on 3/10/2017.
 */

public class Products implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("short_description")
    private String  short_description;
    @SerializedName("price")
    private String price;
    @SerializedName("regular_price")
    private String regular_price;
    @SerializedName("rating_count")
    private String rating_count;
    @SerializedName("images")
    private List<Image> images;
    @SerializedName("featured")
    private boolean featured;
    @SerializedName("quantity")
    private int quantity;

    public boolean isFeatured() {
        return featured;
    }

    public int getId() {
        return id;
    }

    public String getRegular_price() {
        return regular_price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getShort_description() {
        return short_description;
    }

    public String getPrice() {
        return price;
    }

    public String getRating_count() {
        return rating_count;
    }

    public List<Image> getImages() {
        return images;
    }

    public Products(int id, String name, String description, String short_description, String price, String rating_count, List<Image> images) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.short_description = short_description;
        this.price = price;
        this.rating_count = rating_count;
        this.images = images;
    }


    public Products(String name, String description, String short_description, String regular_price, String rating_count) {
        this.name = name;
        this.description = description;
        this.short_description = short_description;
        this.regular_price = regular_price;
        this.rating_count = rating_count;
    }

    public Products(int id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
