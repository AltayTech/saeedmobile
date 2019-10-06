package ir.altaytech.saeedmobile.Model.subclass;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;
@Keep
public class ProductPic {
    @SerializedName("Title")
    private String Title;
    @SerializedName("FilePath")
    private String FilePath;

    public ProductPic(String title, String filePath) {
        Title = title;
        FilePath = filePath;
    }

    public String getTitle() {
        return Title;
    }

    public String getFilePath() {
        return FilePath;
    }
}
