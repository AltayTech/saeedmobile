package ir.altaytech.saeedmobile.Model.other;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by scit on 3/21/17.
 */

public class LineItems {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("product_id")
    private int product_id;
    @SerializedName("quantity")
    private int quantity;

    @SerializedName("total")
    private String total;

    @SerializedName("sku")
    private String sku;
    @SerializedName("price")
    private String price;
    @SerializedName("images")
    private List<Image> images;

    public LineItems(String name, int product_id, int quantity, String total, String price) {
        this.name = name;
        this.product_id = product_id;
        this.quantity = quantity;
        this.total = total;
        this.price = price;
    }

    public LineItems(int product_id, String name, int quantity, String total, String price, List<Image> images) {
        this.product_id = product_id;
        this.name = name;
        this.product_id = product_id;
        this.quantity = quantity;
        this.total = total;
        this.price = price;
        this.images = images;
    }

    public LineItems(int product_id, int quantity) {
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public LineItems(int product_id, int quantity, String total) {
        this.product_id = product_id;
        this.quantity = quantity;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getProduct_id() {
        return product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getTotal() {
        return total;
    }

    public String getSku() {
        return sku;
    }

    public String getPrice() {
        return price;
    }

    public List<Image> getImages() {
        return images;
    }
}
