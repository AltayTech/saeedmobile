package ir.altaytech.saeedmobile.Model;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Altay on 2/7/2018.
 */
@Keep
public class Brand {
    @SerializedName("Id")
    private int Id;

    @SerializedName("Title")
    private String Title;
    @SerializedName("Alt")
    private String Alt;
    @SerializedName("LogoPath")
    private String LogoPath;

    public Brand(int id, String title, String alt, String logoPath) {
        Id = id;
        Title = title;
        Alt = alt;
        LogoPath = logoPath;
    }

    public Brand(int id, String title) {
        Id = id;
        Title = title;
    }

    public int getId() {
        return Id;
    }

    public String getTitle() {
        return Title;
    }

    public String getAlt() {
        return Alt;
    }

    public String getLogoPath() {
        return LogoPath;
    }
}