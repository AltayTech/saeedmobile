package ir.altaytech.saeedmobile.Model.other;

import com.google.gson.annotations.SerializedName;

public class Coupon {


    @SerializedName("id")
    private int id;

    @SerializedName("code")
    private String code;

    @SerializedName("amount")
    private String amount;

    @SerializedName("date_expires")
    private String date_expires	;

    @SerializedName("individual_use")
    private Boolean individual_use;

    @SerializedName("usage_limit")
    private int usage_limit;

    @SerializedName("usage_count")
    private int usage_count;

    @SerializedName("usage_limit_per_user")
    private int usage_limit_per_user;

    @SerializedName("discount_type")
    private String discount_type;

    public Coupon(String code, String amount, Boolean individual_use, int usage_limit,String discount_type) {
        this.code = code;
        this.amount = amount;
        this.individual_use = individual_use;
        this.usage_limit = usage_limit;
        this.discount_type=discount_type;
    }

    public Coupon(String code, String amount, String date_expires, int usage_limit, int usage_count) {
        this.code = code;
        this.amount = amount;
        this.date_expires = date_expires;
        this.usage_limit = usage_limit;
        this.usage_count = usage_count;
    }
    public Coupon(String code, String amount, String date_expires, int usage_limit, int usage_count,String discount_type) {
        this.code = code;
        this.amount = amount;
        this.date_expires = date_expires;
        this.usage_limit = usage_limit;
        this.usage_count = usage_count;
        this.discount_type=discount_type;

    }

    public Coupon(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getAmount() {
        return amount;
    }

    public String getDate_expires() {
        return date_expires;
    }

    public Boolean getIndividual_use() {
        return individual_use;
    }

    public int getUsage_limit() {
        return usage_limit;
    }

    public int getUsage_limit_per_user() {
        return usage_limit_per_user;
    }

    public int getUsage_count() {
        return usage_count;
    }

    public String getDiscount_type() {
        return discount_type;
    }
}
