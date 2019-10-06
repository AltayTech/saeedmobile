package ir.altaytech.saeedmobile.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import ir.altaytech.saeedmobile.Api;
import ir.altaytech.saeedmobile.Model.CustomersAddress;
import ir.altaytech.saeedmobile.Model.ReqString;
import ir.altaytech.saeedmobile.Model.ReqUserRegister;
import ir.altaytech.saeedmobile.Model.User;
import ir.altaytech.saeedmobile.R;
import ir.altaytech.saeedmobile.URLs;
import ir.altaytech.saeedmobile.adapters.AddressAdapter;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AddAddressActivity extends AppCompatActivity {

    ProgressBar progressBar;
    private Button sendNewAddress;
    private EditText editTextTransfereeMobNo;
    private EditText editTextTransfereeName;
    private EditText editTextPostalCode;
    private EditText editTextAddress;
    private EditText editTextAddressTitle;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        progressBar = findViewById(R.id.progressBar);
        sendNewAddress = findViewById(R.id.sendNewAddress);
        editTextTransfereeMobNo = findViewById(R.id.editTextTransfereeMobNo);
        editTextTransfereeName = findViewById(R.id.editTextTransfereeName);
        editTextPostalCode = findViewById(R.id.editTextPostalCode);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextAddressTitle = findViewById(R.id.editTextAddressTitle);
        token = retrieveToken();


        sendNewAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String transfereeMobNoHolder = editTextTransfereeMobNo.getText().toString().trim();
                String transfereeNameHolder = editTextTransfereeName.getText().toString().trim();
                String postalCodeHolder = editTextPostalCode.getText().toString().trim();
                String addressHolder = editTextAddress.getText().toString().trim();
                String addressTitleHolder = editTextAddressTitle.getText().toString().trim();

                sendNewAddress(URLs.ROOT_URL, addressHolder, 8, 2, postalCodeHolder, addressTitleHolder, transfereeMobNoHolder, transfereeNameHolder, 46.00, 46.000);

            }
        });


    }

    private void sendNewAddress(String baseURL, String address, int areaId, int cityId, String postalcode, String title, String transfereeMobNo, String transfereeName, double lat, double lng) {


        progressBar.setVisibility(View.VISIBLE);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        String token1 = "Bearer" + " " + token;

        CustomersAddress customersAddress;
        customersAddress = new CustomersAddress(address,areaId,cityId, postalcode, title, transfereeMobNo, transfereeName,lat,lng);
        Api api = mRetrofit.create(Api.class);

        Call<ReqString> call = api.customersAddressCreate("application/json", token1, customersAddress);

        call.enqueue(new Callback<ReqString>() {
            @Override
            public void onResponse(Call<ReqString> call, Response<ReqString> response) {

                try {

                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), response.message().toString(), Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "درخواست ناموفق، دوباره امتحان کنید", Toast.LENGTH_SHORT).show();

                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ReqString> call, Throwable t) {
                try {
                    Toast.makeText(getApplicationContext(), "خطا در اتصال", Toast.LENGTH_SHORT).show();

                } catch (Exception ignored) {

                }

                progressBar.setVisibility(View.GONE);

            }
        });


    }

    private String retrieveToken() {
        String token;
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("paramValue", MODE_PRIVATE);
        token = prefs.getString("param", null); //0 is the default value.
        return token;
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


}
