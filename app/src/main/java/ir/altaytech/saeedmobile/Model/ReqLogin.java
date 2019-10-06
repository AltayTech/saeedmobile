package ir.altaytech.saeedmobile.Model;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;
@Keep
public class ReqLogin {

    @SerializedName("IsSuccess")
    private Boolean IsSuccess;
    @SerializedName("Value")
    private AuthenticationToken2 Value;
    @SerializedName("Message")
    private String Message;

    public ReqLogin(Boolean isSuccess, AuthenticationToken2 value, String message) {
        IsSuccess = isSuccess;
        Value = value;
        Message = message;
    }

    public Boolean getSuccess() {
        return IsSuccess;
    }

    public AuthenticationToken2 getValue() {
        return Value;
    }

    public String getMessage() {
        return Message;
    }
}
