package ir.altaytech.saeedmobile.Model.other;

import android.support.annotation.Keep;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Altay on 2/7/2018.
 */
@Keep
public class Product2 implements Serializable {
    private int id;
    private String title;
    private String shortdesc;
    private double rating;
    private double price;
    private String image;
    private int num;
    private int quantity;
    private List<Image> images;

    public int getQuantity() {
        return quantity;
    }

    public Product2(int id, String title, int quantity) {

        this.id = id;
        this.title = title;
        this.quantity = quantity;
    }


    public Product2(int id, String title, String shortdesc, double rating, double price, String image) {
        this.id = id;
        this.title = title;
        this.shortdesc = shortdesc;
        this.rating = rating;
        this.price = price;
        this.image = image;
    }

    public Product2(int id, String title, String shortdesc, double rating, double price, String image, int num) {
        this.id = id;
        this.title = title;
        this.shortdesc = shortdesc;
        this.rating = rating;
        this.price = price;
        this.image = image;
        this.num = num;
    }

    public Product2(int id, String title, String shortdesc, double rating, double price, List<Image> images) {
        this.id = id;
        this.title = title;
        this.shortdesc = shortdesc;
        this.rating = rating;
        this.price = price;
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public double getRating() {
        return rating;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public int getNum() {
        return num;
    }

    public List<Image> getImages() {
        return images;
    }
}