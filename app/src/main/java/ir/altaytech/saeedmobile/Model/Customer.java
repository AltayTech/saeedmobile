package ir.altaytech.saeedmobile.Model;


import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SCIT on 3/10/2017.
 */
@Keep
public class Customer {

    @SerializedName("FirstName")
    private String FirstName;
    @SerializedName("LastName")
    private String LastName;
    @SerializedName("UserName")
    private String UserName;
    @SerializedName("CityId")
    private int CityId;
    @SerializedName("ProvinceId")
    private int ProvinceId;
    @SerializedName("IsActive")
    private String IsActive;
    @SerializedName("NationalCode")
    private String NationalCode;
    @SerializedName("IsEmailNewsLatterSubsciber")
    private Boolean IsEmailNewsLatterSubsciber;
    @SerializedName("IsSMSNewsLatterSubsciber")
    private Boolean IsSMSNewsLatterSubsciber;
    @SerializedName("BankCardNo")
    private String BankCardNo;
    @SerializedName("Email")
    private String Email;

    public Customer(String first_name, String lastName, String userName, int cityId, int provinceId, String isActive,
                    String nationalCode, Boolean isEmailNewsLatterSubsciber, Boolean isSMSNewsLatterSubsciber,
                    String bankCardNo, String email) {
        this.FirstName = first_name;
        LastName = lastName;
        UserName = userName;
        CityId = cityId;
        ProvinceId = provinceId;
        IsActive = isActive;
        NationalCode = nationalCode;
        IsEmailNewsLatterSubsciber = isEmailNewsLatterSubsciber;
        IsSMSNewsLatterSubsciber = isSMSNewsLatterSubsciber;
        BankCardNo = bankCardNo;
        Email = email;
    }

    public Customer(String first_name, String lastName, String userName, String isActive, String nationalCode, Boolean isEmailNewsLatterSubsciber, Boolean isSMSNewsLatterSubsciber, String bankCardNo, String email) {
        this.FirstName = first_name;
        LastName = lastName;
        UserName = userName;
        IsActive = isActive;
        NationalCode = nationalCode;
        IsEmailNewsLatterSubsciber = isEmailNewsLatterSubsciber;
        IsSMSNewsLatterSubsciber = isSMSNewsLatterSubsciber;
        BankCardNo = bankCardNo;
        Email = email;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getUserName() {
        return UserName;
    }

    public int getCityId() {
        return CityId;
    }

    public int getProvinceId() {
        return ProvinceId;
    }

    public String getIsActive() {
        return IsActive;
    }

    public String getNationalCode() {
        return NationalCode;
    }

    public Boolean getEmailNewsLatterSubsciber() {
        return IsEmailNewsLatterSubsciber;
    }

    public Boolean getSMSNewsLatterSubsciber() {
        return IsSMSNewsLatterSubsciber;
    }

    public String getBankCardNo() {
        return BankCardNo;
    }

    public String getEmail() {
        return Email;
    }
}
