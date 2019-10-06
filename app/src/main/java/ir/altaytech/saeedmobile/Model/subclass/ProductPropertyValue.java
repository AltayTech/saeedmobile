package ir.altaytech.saeedmobile.Model.subclass;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;
@Keep
public class ProductPropertyValue {
    @SerializedName("PropertyId")
    private int PropertyId;
    @SerializedName("PropertyTitle")
    private String PropertyTitle;
    @SerializedName("PropertyValue")
    private String PropertyValue;

    public ProductPropertyValue(int propertyId, String propertyTitle, String propertyValue) {
        PropertyId = propertyId;
        PropertyTitle = propertyTitle;
        PropertyValue = propertyValue;
    }

    public int getPropertyId() {
        return PropertyId;
    }

    public String getPropertyTitle() {
        return PropertyTitle;
    }

    public String getPropertyValue() {
        return PropertyValue;
    }
}
