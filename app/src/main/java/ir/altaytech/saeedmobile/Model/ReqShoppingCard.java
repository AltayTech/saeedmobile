package ir.altaytech.saeedmobile.Model;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Altay on 2/7/2018.
 */
@Keep
public class ReqShoppingCard implements Serializable {
    @SerializedName("IsSuccess")
    private Boolean IsSuccess;
    @SerializedName("Value")
    private ShoppingCard Value;
    @SerializedName("Message")
    private String Message;

    public ReqShoppingCard(Boolean isSuccess, ShoppingCard value, String message) {
        IsSuccess = isSuccess;
        Value = value;
        Message = message;
    }

    public Boolean getSuccess() {
        return IsSuccess;
    }

    public ShoppingCard getValue() {
        return Value;
    }

    public String getMessage() {
        return Message;
    }
}