package ir.altaytech.saeedmobile.Model;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Altay on 2/7/2018.
 */
@Keep
public class Property {
    @SerializedName("Id")
    private int Id;
    @SerializedName("Title")
    private String Title;

    public Property(int id, String title) {
        Id = id;
        Title = title;
    }

    public int getId() {
        return Id;
    }

    public String getTitle() {
        return Title;
    }
}