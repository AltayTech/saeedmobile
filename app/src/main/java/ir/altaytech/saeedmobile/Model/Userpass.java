package ir.altaytech.saeedmobile.Model;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;
@Keep
public class Userpass {

    @SerializedName("UserName")
    private String UserName;
    @SerializedName("password")
    private String  password;

    public Userpass(String userName, String password) {
        UserName = userName;
        this.password = password;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return password;
    }
}
