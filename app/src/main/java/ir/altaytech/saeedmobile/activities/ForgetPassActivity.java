package ir.altaytech.saeedmobile.activities;

import android.content.Context;
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

import ir.altaytech.saeedmobile.Api;
import ir.altaytech.saeedmobile.Model.ReqForgetPass;
import ir.altaytech.saeedmobile.Model.ReqUserRegister;
import ir.altaytech.saeedmobile.Model.User;
import ir.altaytech.saeedmobile.R;
import ir.altaytech.saeedmobile.URLs;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ForgetPassActivity extends AppCompatActivity {


    Boolean CheckEditText;
    String PasswordHolder;
    ProgressBar progressBar;
    private EditText editTextPhoneNumber;
    private EditText password;
    private EditText password2;
    private Button buttonapproveChangePassword;
    private String Password2Holder;
    private String phoneNumberHolder;
    private EditText editTextVerificationCode;

    private int status;
    private String editTextVerificationCodeHolder;

    private CardView editTextVerificationCodeLayout;
    private CardView editTextPasswordLayout;
    private CardView editTextPassword2Layout;
    private String confirmationCodeHashId;
    private int TimeRemaningForExpire;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        password = findViewById(R.id.editTextPassword);
        password2 = findViewById(R.id.editTextPassword2);
        editTextVerificationCode = findViewById(R.id.editTextVerificationCode);

        editTextVerificationCode.setEnabled(false);
        editTextVerificationCodeLayout = findViewById(R.id.editTextVerificationCodeLayout);
        editTextPasswordLayout = findViewById(R.id.editTextPasswordLayout);
        editTextPassword2Layout = findViewById(R.id.editTextPassword2Layout);

        editTextVerificationCodeLayout.setVisibility(View.INVISIBLE);
        editTextPasswordLayout.setVisibility(View.INVISIBLE);
        editTextPassword2Layout.setVisibility(View.INVISIBLE);


        progressBar = findViewById(R.id.progressBar);

        status = 1;

        // Assigning ID's to Button.
        buttonapproveChangePassword = findViewById(R.id.approveChangePassword);


        // Adding click listener to button.
        buttonapproveChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (status == 1) {

                    CheckEditTextIsEmptyOrNot();

                    if (!TextUtils.isEmpty(phoneNumberHolder)) {

                        sendRestPassReq(URLs.ROOT_URL, phoneNumberHolder);

                    } else {

                        Toast.makeText(ForgetPassActivity.this, "پسوردها یکسان نیست.", Toast.LENGTH_LONG).show();
                        password.requestFocus();

                    }


                } else if (status == 2) {
                    CheckEditTextIsEmptyOrNot();

                    editTextVerificationCodeHolder = editTextVerificationCode.getText().toString().trim();
                    Log.i("sendCode1", editTextVerificationCodeHolder.toString());
                    Log.i("sendCode1", confirmationCodeHashId.toString());

                    if (TextUtils.isEmpty(editTextVerificationCodeHolder)) {

                        Toast.makeText(ForgetPassActivity.this, "کد تایید را وارد کنید", Toast.LENGTH_LONG).show();
                        editTextVerificationCode.requestFocus();

                    } else {

                        sendConfirmationCode(URLs.ROOT_URL);

                    }
                } else if (status == 3) {


                    CheckEditTextIsEmptyOrNot();


                    if (Password2Holder.equals(PasswordHolder)) {
                        sendNewPassword(URLs.ROOT_URL, confirmationCodeHashId, PasswordHolder, Password2Holder);


                    } else {

                        Toast.makeText(ForgetPassActivity.this, "پسوردها یکسان نیست.", Toast.LENGTH_LONG).show();

                    }


                }

            }
        });

    }


    private void sendRestPassReq(String baseURL, String username) {


        progressBar.setVisibility(View.VISIBLE);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        User user;
        user = new User(username);

        Api api = mRetrofit.create(Api.class);

        Call<ReqUserRegister> call = api.fpGenerateConfirmationCode("application/json", user);

        call.enqueue(new Callback<ReqUserRegister>() {
            @Override
            public void onResponse(Call<ReqUserRegister> call, Response<ReqUserRegister> response) {

                try {

                    if (response.isSuccessful()) {
                        status = 2;

                        Log.i("recieveCode", response.body().getValue().getConfirmationCodeHashId());
                        Log.i("recieveCode", String.valueOf(response.body().getValue().getTimeRemaningForExpire()));
                        Log.i("recieveCode", response.message());

                        editTextVerificationCode.setEnabled(true);
                        editTextVerificationCodeLayout.setVisibility(View.VISIBLE);
                        confirmationCodeHashId = response.body().getValue().getConfirmationCodeHashId();
                        TimeRemaningForExpire = response.body().getValue().getTimeRemaningForExpire();

                        buttonapproveChangePassword.setText("ارسال کد تایید");
                    } else {
                        Toast.makeText(getApplicationContext(), response.message().toString(), Toast.LENGTH_SHORT).show();

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

    private void sendConfirmationCode(String baseURL) {


        progressBar.setVisibility(View.VISIBLE);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        Log.i("sendCode", confirmationCodeHashId);

        User user;
        user = new User(confirmationCodeHashId, editTextVerificationCodeHolder);

        Api api = mRetrofit.create(Api.class);

        Call<ReqForgetPass> call = api.fpConfirm("application/json", user);

        call.enqueue(new Callback<ReqForgetPass>() {
            @Override
            public void onResponse(Call<ReqForgetPass> call, Response<ReqForgetPass> response) {
//                Log.i("sendCode", response.message());

                if (response.isSuccessful()) {


                    editTextPasswordLayout.setVisibility(View.VISIBLE);

                    editTextPassword2Layout.setVisibility(View.VISIBLE);

                    status = 3;
                    buttonapproveChangePassword.setText("تغییر پسورد");

//                    Log.i("sendCode", response.message());

                } else {
                    Toast.makeText(getApplicationContext(), response.message().toString(), Toast.LENGTH_SHORT).show();

                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ReqForgetPass> call, Throwable t) {
                try {
                    Log.i("sendCode", t.getMessage().toString());

                    t.printStackTrace();
                    Toast.makeText(getApplicationContext(), "خطا در اتصال", Toast.LENGTH_SHORT).show();

                } catch (Exception ignored) {

                }

                progressBar.setVisibility(View.GONE);

            }
        });


    }

    private void sendNewPassword(String baseURL, String confirmationCodeHashId, String newPass, String newPass2) {


        progressBar.setVisibility(View.VISIBLE);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        Log.i("newpass", confirmationCodeHashId);
        Log.i("newpass", newPass);
        Log.i("newpass", newPass2);

        User user;
        user = new User(confirmationCodeHashId,newPass, newPass2 );

        Api api = mRetrofit.create(Api.class);

        Call<ReqForgetPass> call = api.fpSetNewPassword("application/json", user);

        call.enqueue(new Callback<ReqForgetPass>() {
            @Override
            public void onResponse(Call<ReqForgetPass> call, Response<ReqForgetPass> response) {

                if (response.isSuccessful()) {

                    Log.i("register1", response.message());

                } else {
                    Toast.makeText(getApplicationContext(), response.message().toString(), Toast.LENGTH_SHORT).show();

                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ReqForgetPass> call, Throwable t) {
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
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
