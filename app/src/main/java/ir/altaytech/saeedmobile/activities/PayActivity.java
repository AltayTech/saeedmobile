package ir.altaytech.saeedmobile.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import ir.altaytech.saeedmobile.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PayActivity extends AppCompatActivity {

    private String payURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        WebView webView = findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);

        payURL = retrievePaymentUrl();
        Log.e("url pay", payURL);
        webView.loadUrl(payURL);

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private String retrievePaymentUrl() {
        String token;
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("PaymentUrl", MODE_PRIVATE);
        token = prefs.getString("PaymentUrl", null); //0 is the default value.
        return token;
    }



}
