package ir.altaytech.saeedmobile.Model.subclass;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;
@Keep
public class HighlightedProductPropertyValue {
    @SerializedName("PropertyTitle")
    private String PropertyTitle;
    @SerializedName("PropertyValue")
    private String PropertyValue;

    public HighlightedProductPropertyValue(String propertyTitle, String propertyValue) {
        PropertyTitle = propertyTitle;
        PropertyValue = propertyValue;
    }

    public String getPropertyTitle() {
        return PropertyTitle;
    }

    public String getPropertyValue() {
        return PropertyValue;
    }
}
