package ir.altaytech.saeedmobile.Model;


import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SCIT on 3/10/2017.
 */
@Keep
public class Payment {

    @SerializedName("PaymentUrl")
    private String PaymentUrl;

    public Payment(String paymentUrl) {
        PaymentUrl = paymentUrl;
    }

    public String getPaymentUrl() {
        return PaymentUrl;
    }
}
