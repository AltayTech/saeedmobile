package ir.altaytech.saeedmobile.activities;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import ir.altaytech.saeedmobile.Api;
import ir.altaytech.saeedmobile.Model.ReqUserRegister;
import ir.altaytech.saeedmobile.Model.User;
import ir.altaytech.saeedmobile.Model.other.MetaData;
import ir.altaytech.saeedmobile.R;
import ir.altaytech.saeedmobile.URLs;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RegisterationActivity extends AppCompatActivity {


    Boolean CheckEditText;
    String PasswordHolder;
    ProgressBar progressBar;
    private EditText editTextPhoneNumber;
    private EditText password;
    private EditText password2;
    private Button buttonRegister;
    private String Password2Holder;
    private String phoneNumberHolder;
    private EditText editTextVerificationCode;
    private int status;
    private String editTextVerificationCodeHolder;

    private boolean CheckAlphanumeric;
    private CardView editTextVerificationCodeLayout;

    private String ConfirmationCodeHashId;
    private int TimeRemaningForExpire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        password = findViewById(R.id.editTextPassword);
        password2 = findViewById(R.id.editTextPassword2);
        editTextVerificationCode = findViewById(R.id.editTextVerificationCode);

        editTextVerificationCode.setEnabled(false);
        editTextVerificationCodeLayout = findViewById(R.id.editTextVerificationCodeLayout);
        editTextVerificationCodeLayout.setVisibility(View.INVISIBLE);
        progressBar = findViewById(R.id.progressBar);

        status = 1;

        // Assigning ID's to Button.
        buttonRegister = findViewById(R.id.buttonRegister);


        // Adding click listener to button.
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (status == 1) {
                    CheckEditTextIsEmptyOrNot();
                    isAlphanumeric();
                    if (CheckEditText) {

                        if (Password2Holder.equals(PasswordHolder)) {


                            sendRegisterInfo(URLs.ROOT_URL, phoneNumberHolder, "", "", PasswordHolder, Password2Holder);

                        } else {

                            Toast.makeText(RegisterationActivity.this, "پسوردها یکسان نیست.", Toast.LENGTH_LONG).show();

                        }

                    } else {

                        Toast.makeText(RegisterationActivity.this, "تمامی فیلدها پر نیستند", Toast.LENGTH_LONG).show();

                    }
                } else if (status == 2) {
                    editTextVerificationCodeHolder = editTextVerificationCode.getText().toString().trim();
                    if (TextUtils.isEmpty(editTextVerificationCodeHolder)) {

                        Toast.makeText(RegisterationActivity.this, "کد تایید را وارد کنید", Toast.LENGTH_LONG).show();
                        editTextVerificationCode.requestFocus();

                    } else {

                        status = 1;
                        sendRegisterConfirmationCode(URLs.ROOT_URL, ConfirmationCodeHashId, editTextVerificationCodeHolder);
                    }
                }

            }
        });

    }

    private void sendRegisterInfo(String baseURL, String username, String firstname, String lastname, String password, String repassword) {


        progressBar.setVisibility(View.VISIBLE);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        User user;
        user = new User(username, firstname, lastname, password, repassword);

        Api api = mRetrofit.create(Api.class);

        Call<ReqUserRegister> call = api.sendRegister("application/json", user);

        call.enqueue(new Callback<ReqUserRegister>() {
            @Override
            public void onResponse(Call<ReqUserRegister> call, Response<ReqUserRegister> response) {

                try {


                    if (response.isSuccessful()) {
                        Log.i("register1", response.body().getValue().getConfirmationCodeHashId());
                        Log.i("register1", String.valueOf(response.body().getValue().getTimeRemaningForExpire()));
                        Log.i("register1", response.message());

                        editTextVerificationCode.setEnabled(true);
                        editTextVerificationCodeLayout.setVisibility(View.VISIBLE);
                        ConfirmationCodeHashId = response.body().getValue().getConfirmationCodeHashId();
                        TimeRemaningForExpire = response.body().getValue().getTimeRemaningForExpire();
                        status = 2;

                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMessage().toString(), Toast.LENGTH_LONG).show();

                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "درخواست ناموفق، دوباره امتحان کنید", Toast.LENGTH_SHORT).show();

                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ReqUserRegister> call, Throwable t) {
                try {
                    Toast.makeText(getApplicationContext(), "خطا در اتصال", Toast.LENGTH_SHORT).show();

                } catch (Exception ignored) {

                }

                progressBar.setVisibility(View.GONE);

            }
        });


    }

    private void sendRegisterConfirmationCode(String baseURL, String confirmationCodeHashId, String confirmationCode) {


        progressBar.setVisibility(View.VISIBLE);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();


        User user;
        user = new User(confirmationCodeHashId, confirmationCode);

        Api api = mRetrofit.create(Api.class);

        Call<ReqUserRegister> call = api.sendRegisterConfirm("application/json", user);

        call.enqueue(new Callback<ReqUserRegister>() {
            @Override
            public void onResponse(Call<ReqUserRegister> call, Response<ReqUserRegister> response) {

                if (response.isSuccessful()) {

                    Log.i("register1", response.message());

                } else {
                    Toast.makeText(getApplicationContext(), response.message().toString(), Toast.LENGTH_SHORT).show();

                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ReqUserRegister> call, Throwable t) {
                try {
                    Toast.makeText(getApplicationContext(), "خطا در اتصال", Toast.LENGTH_SHORT).show();

                } catch (Exception ignored) {

                }

                progressBar.setVisibility(View.GONE);

            }
        });


    }

    public void CheckEditTextIsEmptyOrNot() {

        phoneNumberHolder = editTextPhoneNumber.getText().toString().trim();
        PasswordHolder = password.getText().toString().trim();
        Password2Holder = password2.getText().toString().trim();
        CheckEditText = !TextUtils.isEmpty(phoneNumberHolder) && !TextUtils.isEmpty(PasswordHolder) && !TextUtils.isEmpty(Password2Holder);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    public void isAlphanumeric() {

        PasswordHolder = password.getText().toString().trim();

        CheckAlphanumeric = PasswordHolder.matches(".*[A-Za-z].*");

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
