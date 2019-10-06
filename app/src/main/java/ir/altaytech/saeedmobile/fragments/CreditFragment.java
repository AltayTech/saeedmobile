package ir.altaytech.saeedmobile.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.security.acl.AclEntry;
import java.util.concurrent.TimeUnit;

import ir.altaytech.saeedmobile.Api;
import ir.altaytech.saeedmobile.Model.AmazingProduct;
import ir.altaytech.saeedmobile.Model.AuthenticationToken;
import ir.altaytech.saeedmobile.Model.Group;
import ir.altaytech.saeedmobile.Model.Payment;
import ir.altaytech.saeedmobile.Model.Product;
import ir.altaytech.saeedmobile.Model.ReqMainPage;
import ir.altaytech.saeedmobile.Model.ReqPayment;
import ir.altaytech.saeedmobile.Model.ReqWalletBalance;
import ir.altaytech.saeedmobile.Model.Slider;
import ir.altaytech.saeedmobile.R;
import ir.altaytech.saeedmobile.SharedPrefManagerCustomer;
import ir.altaytech.saeedmobile.SharedPrefManagerLogin;
import ir.altaytech.saeedmobile.URLs;
import ir.altaytech.saeedmobile.activities.MainActivity;
import ir.altaytech.saeedmobile.activities.PayActivity;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Altay on 2/7/2018.
 */

public class CreditFragment extends Fragment {

    View rootView;
    private TextView creditTextView;
    private int customerId;
    private ProgressBar progressBar;
    private String token;
    Dialog myDialog;
    private String editTextCreditHolder;
    Button increaseCreditBTN;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_credit, container, Boolean.parseBoolean(null));

        creditTextView = rootView.findViewById(R.id.creditTextView);
        increaseCreditBTN = rootView.findViewById(R.id.increaseCredit);
        progressBar = rootView.findViewById(R.id.progressBar);
        myDialog = new Dialog(getContext());

        token = retrieveToken();
        Log.d("tokenCredit", token);

        try {

            balanceReq(URLs.ROOT_URL, token);

        } catch (Exception e) {

            Log.d("creditReqError", e.getMessage());
        }
        increaseCreditBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowPopup();
            }
        });
        return rootView;

    }

    private void balanceReq(String baseURL, String token) {
        progressBar.setVisibility(View.VISIBLE);


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        String token1 = "Bearer" + " " + token;

        Api api = mRetrofit.create(Api.class);

        Call<ReqWalletBalance> call = api.getBalance("application/json", token1);

        call.enqueue(new Callback<ReqWalletBalance>() {
            @Override
            public void onResponse(Call<ReqWalletBalance> call, Response<ReqWalletBalance> response) {

                try {

                    Log.i("productM", response.message());

                    Log.i("productM", String.valueOf(response.body().getValue().getBalance()));


                    if (response.isSuccessful()) {
                        int balance = response.body().getValue().getBalance();
                        creditTextView.setText(balance);
                    } else {
                        try {
                            Toast.makeText(getContext(), "خطا در اتصال", Toast.LENGTH_SHORT).show();

                        } catch (Exception ignored) {

                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progressBar.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<ReqWalletBalance> call, Throwable t) {
                try {

                    Log.i("retrofiterror", t.getMessage());

                    Toast.makeText(getContext(), "خطا در اتصال3", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                progressBar.setVisibility(View.GONE);

            }
        });


    }

    public void ShowPopup() {
        final TextView editTextCredit;
        Button btnAccept, btnCancel;
        myDialog.setContentView(R.layout.dialog_custompopup);
        btnAccept = myDialog.findViewById(R.id.btnAccept);
        btnCancel = myDialog.findViewById(R.id.btnCancel);
        editTextCredit = myDialog.findViewById(R.id.editTextVerificationCode);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextCreditHolder = editTextCredit.getText().toString().trim();

                if (TextUtils.isEmpty(editTextCreditHolder)) {
                    Toast.makeText(getContext(), "کد تایید را وارد کنید", Toast.LENGTH_LONG).show();

                    editTextCredit.requestFocus();

                } else {

                    increaseBalanceReq(URLs.ROOT_URL, token, editTextCreditHolder);
                    myDialog.dismiss();

                }


            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    private String retrieveToken() {
        String token;
        SharedPreferences prefs = getContext().getSharedPreferences("paramValue", MODE_PRIVATE);
        token = prefs.getString("param", null); //0 is the default value.
        return token;
    }

    private void increaseBalanceReq(String baseURL, String token, String TotalAmount) {
        progressBar.setVisibility(View.VISIBLE);


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        String token1 = "Bearer" + " " + token;

        Api api = mRetrofit.create(Api.class);
        int TotalAmount1 = Integer.parseInt(TotalAmount);
        Call<ReqPayment> call = api.getBankPaymentUrl("application/json", token1, TotalAmount1);

        call.enqueue(new Callback<ReqPayment>() {
            @Override
            public void onResponse(Call<ReqPayment> call, Response<ReqPayment> response) {

                try {


                    if (response.isSuccessful()) {
                        savePaymentUrl(response.body().getValue().getPaymentUrl());

                        //starting the profile activity
                        startActivity(new Intent(getContext(), PayActivity.class));

                    } else {
                        try {
                            Toast.makeText(getContext(), "خطا در اتصال", Toast.LENGTH_SHORT).show();

                        } catch (Exception ignored) {
                            ignored.printStackTrace();
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "خطا در اتصال", Toast.LENGTH_SHORT).show();

                }
                progressBar.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<ReqPayment> call, Throwable t) {
                try {

                    Log.i("retrofiterror", t.getMessage());

                    Toast.makeText(getContext(), "خطا در اتصال3", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                progressBar.setVisibility(View.GONE);

            }
        });


    }

    private void savePaymentUrl(String PaymentUrl) {
        SharedPreferences.Editor editor = getContext().getSharedPreferences("PaymentUrl", MODE_PRIVATE).edit();
        editor.putString("PaymentUrl", PaymentUrl);
        editor.apply();
    }

}