package ir.altaytech.saeedmobile.Model;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Altay on 2/7/2018.
 */
@Keep
public class Slider {
    @SerializedName("Id")
    private int Id;
    @SerializedName("Inx")
    private int Inx;
    @SerializedName("Title")
    private String Title;
    @SerializedName("Alt")
    private String Alt;
    @SerializedName("PicPath")
    private String PicPath;

    public Slider(int id, int inx, String title, String alt, String picPath) {
        Id = id;
        Inx = inx;
        Title = title;
        Alt = alt;
        PicPath = picPath;
    }

    public int getId() {
        return Id;
    }

    public int getInx() {
        return Inx;
    }

    public String getTitle() {
        return Title;
    }

    public String getAlt() {
        return Alt;
    }

    public String getPicPath() {
        return PicPath;
    }
}