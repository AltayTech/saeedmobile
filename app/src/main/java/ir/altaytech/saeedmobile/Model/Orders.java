package ir.altaytech.saeedmobile.Model;


import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SCIT on 3/10/2017.
 */
@Keep
public class Orders {
    @SerializedName("Id")
    private String Id;
    @SerializedName("RegDateTime")
    private String RegDateTime;

    @SerializedName("Total")
    private int Total;

    public Orders(String id, String regDateTime, int total) {
        Id = id;
        RegDateTime = regDateTime;
        Total = total;
    }

    public String getId() {
        return Id;
    }

    public String getRegDateTime() {
        return RegDateTime;
    }

    public int getTotal() {
        return Total;
    }
}
