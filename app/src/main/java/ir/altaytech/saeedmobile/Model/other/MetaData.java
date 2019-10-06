package ir.altaytech.saeedmobile.Model.other;

import com.google.gson.annotations.SerializedName;

public class MetaData {

    @SerializedName("key")
    private String key;

    @SerializedName("value")
    private String value;


    public MetaData(String key, String value) {
        this.key = key;
        this.value = value;
    }


    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
