package ir.altaytech.saeedmobile.Model;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Altay on 2/7/2018.
 */
@Keep
public class ReqUserRegister implements Serializable {
    @SerializedName("IsSuccess")
    private Boolean IsSuccess;
    @SerializedName("Value")
    private User Value;
    @SerializedName("Message")
    private String Message;

    public ReqUserRegister(Boolean isSuccess, User value, String message) {
        IsSuccess = isSuccess;
        Value = value;
        Message = message;
    }

    public Boolean getSuccess() {
        return IsSuccess;
    }

    public User getValue() {
        return Value;
    }

    public String getMessage() {
        return Message;
    }
}