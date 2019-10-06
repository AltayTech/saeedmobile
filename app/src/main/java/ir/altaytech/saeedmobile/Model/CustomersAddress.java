package ir.altaytech.saeedmobile.Model;


import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SCIT on 3/10/2017.
 */
@Keep
public class CustomersAddress {

    @SerializedName("Id")
    private int Id;
    @SerializedName("AreaName")
    private String AreaName;
    @SerializedName("FullLocation")
    private String FullLocation;

    @SerializedName("Address")
    private String Address;
    @SerializedName("AreaId")
    private int AreaId;

    @SerializedName("CityId")
    private int CityId;
    @SerializedName("PostalCode")
    private String PostalCode;
    @SerializedName("Title")
    private String Title;
    @SerializedName("TransfereeMobNo")
    private String TransfereeMobNo;
    @SerializedName("TransfereeName")
    private String TransfereeName;
    @SerializedName("Lat")
    private double Lat;

    @SerializedName("Lng")
    private double Lng;

    @SerializedName("CityName")
    private String CityName;



    public CustomersAddress(int id, String areaName, String fullLocation, String address, int areaId, int cityId, String postalCode, String title, String transfereeMobNo, String transfereeName, double lat, double lng) {
        Id = id;
        AreaName = areaName;
        FullLocation = fullLocation;
        Address = address;
        AreaId = areaId;
        CityId = cityId;
        PostalCode = postalCode;
        Title = title;
        TransfereeMobNo = transfereeMobNo;
        TransfereeName = transfereeName;
        Lat = lat;
        Lng = lng;
    }

    public CustomersAddress(String areaName, int areaId, int cityId, String cityName) {
        AreaName = areaName;
        AreaId = areaId;
        CityId = cityId;
        CityName = cityName;
    }

    public CustomersAddress(String address, String postalCode, String title, String transfereeMobNo, String transfereeName) {
        Address = address;
        PostalCode = postalCode;
        Title = title;
        TransfereeMobNo = transfereeMobNo;
        TransfereeName = transfereeName;
    }

    public CustomersAddress(String address, int areaId, int cityId, String postalCode, String title, String transfereeMobNo, String transfereeName, double lat, double lng) {
        Address = address;
        AreaId = areaId;
        CityId = cityId;
        PostalCode = postalCode;
        Title = title;
        TransfereeMobNo = transfereeMobNo;
        TransfereeName = transfereeName;
        Lat = lat;
        Lng = lng;
    }

    public CustomersAddress(int id, String areaName, String fullLocation, String address, int areaId, int cityId, String postalCode, String title, String transfereeMobNo, String transfereeName, double lat, double lng, String cityName) {
        Id = id;
        AreaName = areaName;
        FullLocation = fullLocation;
        Address = address;
        AreaId = areaId;
        CityId = cityId;
        PostalCode = postalCode;
        Title = title;
        TransfereeMobNo = transfereeMobNo;
        TransfereeName = transfereeName;
        Lat = lat;
        Lng = lng;
        CityName = cityName;
    }

    public int getId() {
        return Id;
    }

    public String getAreaName() {
        return AreaName;
    }

    public String getFullLocation() {
        return FullLocation;
    }

    public String getAddress() {
        return Address;
    }

    public int getAreaId() {
        return AreaId;
    }

    public int getCityId() {
        return CityId;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public String getTitle() {
        return Title;
    }

    public String getTransfereeMobNo() {
        return TransfereeMobNo;
    }

    public String getTransfereeName() {
        return TransfereeName;
    }

    public double getLat() {
        return Lat;
    }

    public double getLng() {
        return Lng;
    }

    public String getCityName() {
        return CityName;
    }
}
