package ir.altaytech.saeedmobile.Model;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.util.List;
@Keep
public class ReqOBJ {

    @SerializedName("IsSuccess")
    private Boolean IsSuccess;
    @SerializedName("Value")
    private List<Object> Value;
    @SerializedName("Message")
    private String Message;

    public ReqOBJ(Boolean isSuccess, List<Object> value, String message) {
        IsSuccess = isSuccess;
        Value = value;
        Message = message;
    }

    public Boolean getSuccess() {
        return IsSuccess;
    }

    public List<Object> getValue() {
        return Value;
    }

    public String getMessage() {
        return Message;
    }

}
