package ir.altaytech.saeedmobile.Model;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Altay on 2/7/2018.
 */
@Keep
public class ReqCustomerAllAddress implements Serializable {
    @SerializedName("IsSuccess")
    private Boolean IsSuccess;
    @SerializedName("Value")
    private List<CustomersAddress> Value;
    @SerializedName("Message")
    private String Message;

    public ReqCustomerAllAddress(Boolean isSuccess, List<CustomersAddress> value, String message) {
        IsSuccess = isSuccess;
        Value = value;
        Message = message;
    }

    public Boolean getSuccess() {
        return IsSuccess;
    }

    public List<CustomersAddress> getValue() {
        return Value;
    }

    public String getMessage() {
        return Message;
    }
}