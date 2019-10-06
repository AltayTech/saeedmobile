package ir.altaytech.saeedmobile.Model.other;

import com.google.gson.annotations.SerializedName;

public class Shipping {


    @SerializedName("first_name")
    private String first_name;
    @SerializedName("last_name")
    private String last_name;
    @SerializedName("company")
    private String company;
    @SerializedName("address_1")
    private String address_1;
    @SerializedName("address_2")
    private String address_2;
    @SerializedName("city")
    private String city;
    @SerializedName("state")
    private String state;
    @SerializedName("postcode")
    private String postcode;
    @SerializedName("country")

    private String country;


    public Shipping(String first_name, String last_name, String address_1, String city) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.address_1 = address_1;
        this.city = city;
    }

    public Shipping(String first_name, String last_name, String company, String address_1, String address_2, String city, String state, String postcode, String country) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.company = company;
        this.address_1 = address_1;
        this.address_2 = address_2;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
        this.country = country;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress_1() {
        return address_1;
    }

    public String getAddress_2() {
        return address_2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCountry() {
        return country;
    }
}
