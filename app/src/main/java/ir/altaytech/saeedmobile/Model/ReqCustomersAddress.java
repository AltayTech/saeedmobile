package ir.altaytech.saeedmobile.Model;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Altay on 2/7/2018.
 */
@Keep
public class ReqCustomersAddress implements Serializable {
    @SerializedName("IsSuccess")
    private Boolean IsSuccess;
    @SerializedName("Value")
    private CustomersAddress Value;
    @SerializedName("Message")
    private String Message;

    public ReqCustomersAddress(Boolean isSuccess, CustomersAddress value, String message) {
        IsSuccess = isSuccess;
        Value = value;
        Message = message;
    }

    public Boolean getSuccess() {
        return IsSuccess;
    }

    public CustomersAddress getValue() {
        return Value;
    }

    public String getMessage() {
        return Message;
    }
}