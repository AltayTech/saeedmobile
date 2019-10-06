package ir.altaytech.saeedmobile.Model;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Altay on 2/7/2018.
 */
@Keep
public class ReqForgetPass implements Serializable {
    @SerializedName("IsSuccess")
    private Boolean IsSuccess;
    @SerializedName("Value")
    private String Value;
    @SerializedName("Message")
    private String Message;

    public ReqForgetPass(Boolean isSuccess, String value, String message) {
        IsSuccess = isSuccess;
        Value = value;
        Message = message;
    }

    public Boolean getSuccess() {
        return IsSuccess;
    }

    public String getValue() {
        return Value;
    }

    public String getMessage() {
        return Message;
    }
}