package ir.altaytech.saeedmobile.Model.other;


import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by SCIT on 3/10/2017.
 */
@Keep
public class Customers implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("first_name")
    private String first_name;
    @SerializedName("last_name")
    private String last_name;
    @SerializedName("email")
    private String email;
    @SerializedName("username")

    private String username;
    @SerializedName("password")

    private String password;

    @SerializedName("billing")
    private Billing billing;

    @SerializedName("phone")
    private String phone;

    @SerializedName("address_1")
    private String address_1;

    @SerializedName("meta_data")
    private List<MetaData> meta_data;

    @SerializedName("representer")
    private String representer;


    public Customers(String first_name, String last_name, String email, String username, String password, Billing billing) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.billing = billing;
    }

    public Customers(String first_name, String last_name, String email, Billing billing) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.billing = billing;
    }


    public Customers(int id, String first_name, String last_name, String email, String username, String phone, String address_1) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.username = username;
        this.phone = phone;
        this.address_1 = address_1;
    }

    public Customers(int id, String first_name, String last_name, String email, String username, String phone, String address_1, String representer) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.username = username;
        this.phone = phone;
        this.address_1 = address_1;
        this.representer = representer;

    }

    public Customers(String first_name, String email, String username, String password, String phone) {
        this.first_name = first_name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    public Customers(int id, String first_name, String last_name, String email, String username, String phone, String address_1, List<MetaData> meta_data) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.username = username;
        this.phone = phone;
        this.address_1 = address_1;
        this.meta_data = meta_data;

    }

    public Customers(String first_name, String last_name, String email, String phone, String password, Billing billing, List<MetaData> meta_data) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.billing = billing;
        this.phone = phone;
        this.meta_data = meta_data;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress_1() {
        return address_1;
    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<MetaData> getMeta_data() {
        return meta_data;
    }

    public String getRepresenter() {
        return representer;
    }
}
