package ir.altaytech.saeedmobile.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import ir.altaytech.saeedmobile.Model.Customer;
import ir.altaytech.saeedmobile.Model.Orders;
import ir.altaytech.saeedmobile.Model.ReqProfileUpdate;
import ir.altaytech.saeedmobile.Model.ReqTransaction;
import ir.altaytech.saeedmobile.Model.Transaction;
import ir.altaytech.saeedmobile.Model.other.Product2;
import ir.altaytech.saeedmobile.SharedPrefManagerLogin;
import ir.altaytech.saeedmobile.activities.LoginActivity;
import ir.altaytech.saeedmobile.activities.MainActivity;
import ir.altaytech.saeedmobile.activities.ProfileActivity;
import ir.altaytech.saeedmobile.Api;
import ir.altaytech.saeedmobile.CouponService;
import ir.altaytech.saeedmobile.DatabaseManager;
import ir.altaytech.saeedmobile.Model.other.Customers;
import ir.altaytech.saeedmobile.Model.other.Shipping;
import ir.altaytech.saeedmobile.Model.other.Billing;
import ir.altaytech.saeedmobile.Model.other.LineItems;
import ir.altaytech.saeedmobile.R;
import ir.altaytech.saeedmobile.RetrofitApi;
import ir.altaytech.saeedmobile.SharedPrefManagerCustomer;
import ir.altaytech.saeedmobile.URLs;
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

public class UserInfoFragment extends Fragment {

    List<Product2> shoppingCartProductList;
    private TextView editTextUserNameC;
    private TextView editTextFirstNameC;
    private TextView editTextLastNameC;
    private Button declineCorrectProfile;
    private EditText editTextPhoneNumberC;
    private Button changeProfile;
    private Customer customer;
    private String firstNameHolder;
    private String lastNameHolder;
    private String userNameHolder;
    private String emailHolder;
    private boolean CheckEditText;
    private String phoneNumberHolder;

    private View rootView;

    private ProgressBar progressBar;
    private String token;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_user_info, container, Boolean.parseBoolean(null));


        if (!SharedPrefManagerLogin.getInstance(getContext()).isLoggedIn()) {
            startActivity(new Intent(getContext(), LoginActivity.class));
            getActivity().finish();
        }


        editTextUserNameC = rootView.findViewById(R.id.editTextUserNameC);
        editTextFirstNameC = rootView.findViewById(R.id.editTextFirstNameC);
        editTextLastNameC = rootView.findViewById(R.id.editTextLastNameC);
        editTextPhoneNumberC = rootView.findViewById(R.id.editTextPhoneNumberC);
        declineCorrectProfile = rootView.findViewById(R.id.declineCorrectProfile);
        changeProfile = rootView.findViewById(R.id.changeProfile);
        shoppingCartProductList = new ArrayList<>();
        progressBar = rootView.findViewById(R.id.progressBar);

        //getting the current user
        customer = SharedPrefManagerCustomer.getInstance(getContext()).getUser();

        editTextUserNameC.setText(customer.getUserName());
        editTextFirstNameC.setText(customer.getFirstName());
        editTextLastNameC.setText(customer.getLastName());
        editTextPhoneNumberC.setText(customer.getUserName());

        token = retrieveToken();

        declineCorrectProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ProfileActivity.class));

            }
        });
        changeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckEditTextIsEmptyOrNot();
                if (CheckEditText) {

                    profileUpdateReq(URLs.ROOT_URL, token);

                } else {

                    Toast.makeText(getContext(), "تمامی فیلدها پر نیستند", Toast.LENGTH_LONG).show();

                }
            }
        });
        return rootView;
    }

    public void CheckEditTextIsEmptyOrNot() {

        // Getting values from EditText.
        firstNameHolder = editTextFirstNameC.getText().toString().trim();
        lastNameHolder = editTextLastNameC.getText().toString().trim();
        userNameHolder = editTextUserNameC.getText().toString().trim();
        emailHolder = customer.getEmail();
        phoneNumberHolder = editTextPhoneNumberC.getText().toString().trim();

        // Checking whether EditText value is empty or not.
        CheckEditText = !TextUtils.isEmpty(firstNameHolder) && !TextUtils.isEmpty(lastNameHolder);
    }


    private void profileUpdateReq(String baseURL, String token) {

        CheckEditTextIsEmptyOrNot();

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

        Customer customer;
        customer = new Customer(
                firstNameHolder,
                lastNameHolder,
                userNameHolder,
                "فعال",
                "1",
                true,
                true,
                "111111111111",
                ""
        );


        String token1 = "Bearer" + " " + token;

        Api api = mRetrofit.create(Api.class);

        Call<ReqProfileUpdate> call = api.customersProfileUpdate("application/json", token1, customer);

        call.enqueue(new Callback<ReqProfileUpdate>() {
            @Override
            public void onResponse(Call<ReqProfileUpdate> call, Response<ReqProfileUpdate> response) {

                try {

                    Log.i("productM", response.message());

                    Log.i("productM", String.valueOf(response.body().getValue()));


                    if (response.isSuccessful()) {

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
            public void onFailure(Call<ReqProfileUpdate> call, Throwable t) {
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

    private String retrieveToken() {
        String token;
        SharedPreferences prefs = getContext().getSharedPreferences("paramValue", MODE_PRIVATE);
        token = prefs.getString("param", null); //0 is the default value.
        return token;
    }
}
