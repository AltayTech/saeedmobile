package ir.altaytech.saeedmobile.Model;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;
@Keep
public class AuthenticationToken2 {

    @SerializedName("Token")
    private String Token;
    @SerializedName("UserFullName")
    private String UserFullName;
    @SerializedName("UserName")
    private String UserName;


    public AuthenticationToken2(String token, String userFullName, String userName) {
        Token = token;
        UserFullName = userFullName;
        UserName = userName;
    }

    public String getToken() {
        return Token;
    }

    public String getUserFullName() {
        return UserFullName;
    }

    public String getUserName() {
        return UserName;
    }
}
