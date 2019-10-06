package ir.altaytech.saeedmobile.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Random;

import ir.altaytech.saeedmobile.Api;
import ir.altaytech.saeedmobile.Model.Customer;
import ir.altaytech.saeedmobile.Model.ReqCustomer;
import ir.altaytech.saeedmobile.Model.ReqForgetPass;
import ir.altaytech.saeedmobile.Model.other.Customers;
import ir.altaytech.saeedmobile.R;
import ir.altaytech.saeedmobile.RetrofitApi;
import ir.altaytech.saeedmobile.SharedPrefManagerCustomer;
import ir.altaytech.saeedmobile.URLs;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ChangePassActivity extends AppCompatActivity {

    private EditText editTextOldPassword;
    private EditText editTextNewPassword;
    private EditText editTextNewRePassword;
    private Button approveChangePassword;
    private EditText editTextVerificationCode;

    ProgressBar progressBar;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        progressBar = findViewById(R.id.progressBar);

        editTextOldPassword = findViewById(R.id.editTextOldPassword);

        editTextNewPassword = findViewById(R.id.editTextNewPassword);
        editTextNewRePassword = findViewById(R.id.editTextNewRePassword);
        approveChangePassword = findViewById(R.id.approveChangePassword);
        editTextVerificationCode = findViewById(R.id.editTextVerificationCode);

        editTextVerificationCode.setEnabled(false);
        editTextVerificationCode.setBackgroundColor(Color.TRANSPARENT);

        token = retrieveToken();

        approveChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String oldPassword = editTextOldPassword.getText().toString().trim();
                String newPassword = editTextNewPassword.getText().toString().trim();
                String newRePassword = editTextNewRePassword.getText().toString().trim();


                if (newPassword.equals(newRePassword)) {
                    reqCustomers(URLs.ROOT_URL, token, oldPassword, newPassword, newRePassword);


                } else {

                    editTextVerificationCode.setError("لطفا کد تایید را پس از دریافت وارد نمایید");
                    editTextVerificationCode.requestFocus();

                }


            }
        });


    }


    private void reqCustomers(String baseURL, String token, String oldPassword, String newPassword, String newRePassword) {

        progressBar.setVisibility(View.VISIBLE);


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        String token1 = "Bearer" + " " + token;

        Api api = mRetrofit.create(Api.class);
        Call<ReqForgetPass> call = api.changePassword("application/json", token1, oldPassword, newPassword, newRePassword);

        call.enqueue(new Callback<ReqForgetPass>() {

            @Override
            public void onResponse(Call<ReqForgetPass> call, Response<ReqForgetPass> response) {
                progressBar.setVisibility(View.GONE);

                try {

                    //starting the profile activity
                    finish();
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ReqForgetPass> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("retrofit error", t.getMessage());
                progressBar.setVisibility(View.GONE);

            }
        });

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

    private String retrieveToken() {
        String token;
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("paramValue", MODE_PRIVATE);
        token = prefs.getString("param", null); //0 is the default value.
        return token;
    }

}
