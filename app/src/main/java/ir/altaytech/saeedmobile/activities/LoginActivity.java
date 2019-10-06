package ir.altaytech.saeedmobile.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import ir.altaytech.saeedmobile.Api;
import ir.altaytech.saeedmobile.Model.Customer;
import ir.altaytech.saeedmobile.Model.ReqCustomer;
import ir.altaytech.saeedmobile.Model.ReqLogin;
import ir.altaytech.saeedmobile.Model.Userpass;
import ir.altaytech.saeedmobile.R;
import ir.altaytech.saeedmobile.SharedPrefManagerCustomer;
import ir.altaytech.saeedmobile.SharedPrefManagerLogin;
import ir.altaytech.saeedmobile.URLs;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class LoginActivity extends AppCompatActivity {

    EditText editTextUsername, editTextPassword;
    ProgressBar progressBar;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if (SharedPrefManagerLogin.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        progressBar = findViewById(R.id.progressBar);


        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);

        findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });

        //if user presses on not registered
        findViewById(R.id.buttonRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent registerationIntent = new Intent(getApplicationContext(), RegisterationActivity.class);
                startActivity(registerationIntent);
            }
        });

        findViewById(R.id.textViewForgetPass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent forgetPassIntent = new Intent(getApplicationContext(), ForgetPassActivity.class);
                startActivity(forgetPassIntent);
            }
        });
    }


    private void userLogin() {
        //first getting the values
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        //validating inputs
        if (TextUtils.isEmpty(username)) {
            editTextUsername.setError("لطفا شماره موبایل خود را وارد نمایید.");
            editTextUsername.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("لطفا پسورد را وارد نمایید");
            editTextPassword.requestFocus();
            return;
        }

        loginReq(URLs.ROOT_URL, username, password);

    }

    private void loginReq(String baseURL, String username, String password) {


        progressBar.setVisibility(View.VISIBLE);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        Userpass userpass;
        userpass = new Userpass(username, password);

        Api api = mRetrofit.create(Api.class);

        Call<ReqLogin> call = api.login2("application/json", userpass);

        call.enqueue(new Callback<ReqLogin>() {
            @Override
            public void onResponse(Call<ReqLogin> call, Response<ReqLogin> response) {

                if (response.isSuccessful()) {
                    try {
                        token = response.body().getValue().getToken();

                        saveToken(token);
                        SharedPrefManagerLogin.getInstance(getApplicationContext()).userLogin(response.body().getValue());


                        Toast.makeText(getApplicationContext(), response.body().getValue().getToken(), Toast.LENGTH_SHORT).show();
                        reqCustomers(URLs.ROOT_URL, token);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ReqLogin> call, Throwable t) {
                try {
                    Toast.makeText(getApplicationContext(), "خطا در اتصال", Toast.LENGTH_SHORT).show();

                } catch (Exception ignored) {

                }

                progressBar.setVisibility(View.GONE);

            }
        });


    }

    private void reqCustomers(String baseURL, String token) {
        progressBar.setVisibility(View.VISIBLE);


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        String token1 = "Bearer" + " " + token;

        Api api = mRetrofit.create(Api.class);
        Call<ReqCustomer> call = api.getCustomers("application/json", token1);

        call.enqueue(new Callback<ReqCustomer>() {

            @Override
            public void onResponse(Call<ReqCustomer> call, Response<ReqCustomer> response) {
                progressBar.setVisibility(View.GONE);

                try {
                    Customer customer = response.body().getValue();
                    SharedPrefManagerCustomer.getInstance(getApplicationContext()).userLogin(customer);

                    //starting the profile activity
                    finish();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));

                    Log.i("token", response.body().getValue().getUserName());
                    Log.i("token", response.body().getSuccess().toString());
                    Log.i("token", response.message());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //storing the user in shared preferences


            }

            @Override
            public void onFailure(Call<ReqCustomer> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("retrofit error", t.getMessage());
                progressBar.setVisibility(View.GONE);

            }
        });

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private boolean isValidMail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private String retrieveToken() {
        String token;
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("paramValue", MODE_PRIVATE);
        token = prefs.getString("param", null); //0 is the default value.
        return token;
    }

    private void saveToken(String token) {
        SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("paramValue", MODE_PRIVATE).edit();
        editor.putString("param", token);
        editor.apply();
    }

}