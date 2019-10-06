package ir.altaytech.saeedmobile.Model;


import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SCIT on 3/10/2017.
 */
@Keep
public class User {

    @SerializedName("UserName")
    private String UserName;
    @SerializedName("FirstName")
    private String first_name;
    @SerializedName("LastName")
    private String LastName;
    @SerializedName("Password")
    private String Password;
    @SerializedName("RePassword")
    private String RePassword;

    @SerializedName("ConfirmationCodeHashId")
    private String ConfirmationCodeHashId;
    @SerializedName("TimeRemaningForExpire")
    private int TimeRemaningForExpire;

    @SerializedName("SendCode")
    private String SendCode;

    @SerializedName("Balance")
    private int Balance;



    public User(String userName, String first_name, String lastName, String password, String rePassword) {
        UserName = userName;
        this.first_name = first_name;
        LastName = lastName;
        Password = password;
        RePassword = rePassword;
    }

    public User(String userName) {
        UserName = userName;
    }

    public User( String confirmationCodeHashId,String password, String rePassword) {
        ConfirmationCodeHashId = confirmationCodeHashId;

        Password = password;
        RePassword = rePassword;
    }

    public User(String confirmationCodeHashId, String sendCode) {
        ConfirmationCodeHashId = confirmationCodeHashId;
        SendCode = sendCode;
    }

    public User(int balance) {
        Balance = balance;
    }

    public int getBalance() {
        return Balance;
    }

    public String getUserName() {
        return UserName;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLastName() {
        return LastName;
    }

    public String getPassword() {
        return Password;
    }

    public String getRePassword() {
        return RePassword;
    }

    public String getConfirmationCodeHashId() {
        return ConfirmationCodeHashId;
    }

    public int getTimeRemaningForExpire() {
        return TimeRemaningForExpire;
    }

    public String getSendCode() {
        return SendCode;
    }
}
