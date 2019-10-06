package ir.altaytech.saeedmobile.Model;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Altay on 2/7/2018.
 */
@Keep
public class ReqOrderList implements Serializable {
    @SerializedName("IsSuccess")
    private Boolean IsSuccess;
    @SerializedName("Value")
    private List<Orders> Value;
    @SerializedName("Message")
    private String Message;

    public ReqOrderList(Boolean isSuccess, List<Orders> value, String message) {
        IsSuccess = isSuccess;
        Value = value;
        Message = message;
    }

    public Boolean getSuccess() {
        return IsSuccess;
    }

    public List<Orders> getValue() {
        return Value;
    }

    public String getMessage() {
        return Message;
    }
}