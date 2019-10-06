package ir.altaytech.saeedmobile.Model;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;
@Keep
public class ReqCustomer {

    @SerializedName("IsSuccess")
    private Boolean IsSuccess;
    @SerializedName("Value")
    private Customer Value;
    @SerializedName("Message")
    private String Message;

    public ReqCustomer(Boolean isSuccess, Customer value, String message) {
        IsSuccess = isSuccess;
        Value = value;
        Message = message;
    }

    public Boolean getSuccess() {
        return IsSuccess;
    }

    public Customer getValue() {
        return Value;
    }

    public String getMessage() {
        return Message;
    }
}
