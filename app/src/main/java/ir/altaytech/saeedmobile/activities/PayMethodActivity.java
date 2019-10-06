package ir.altaytech.saeedmobile.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import ir.altaytech.saeedmobile.Api;
import ir.altaytech.saeedmobile.DatabaseManager;
import ir.altaytech.saeedmobile.Model.AuthenticationToken;
import ir.altaytech.saeedmobile.Model.CustomersAddress;
import ir.altaytech.saeedmobile.Model.ReqCustomerAllAddress;
import ir.altaytech.saeedmobile.Model.ReqString;
import ir.altaytech.saeedmobile.Model.other.MetaData;
import ir.altaytech.saeedmobile.Model.other.Product2;
import ir.altaytech.saeedmobile.R;
import ir.altaytech.saeedmobile.SharedPrefManagerCustomer;
import ir.altaytech.saeedmobile.SharedPrefManagerLogin;
import ir.altaytech.saeedmobile.URLs;
import ir.altaytech.saeedmobile.adapters.AddressAdapter;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PayMethodActivity extends AppCompatActivity {

    ProgressBar progressBar;
    private Button directBtn;
    private Button onlinePayBtn;
    private TextView totalPriceTextView;
    private TextView editTextAddressTitle;
    private TextView address;
    private TextView transfereeMobNo;
    private TextView transfereeName;
    private TextView postalCode;
    private LinearLayout changeAddress;
    Dialog myDialog;
    private String token;
    private List<CustomersAddress> addressList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_method);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        directBtn = findViewById(R.id.dirctOrder);
        onlinePayBtn = findViewById(R.id.onlinePay);

        progressBar = findViewById(R.id.progressBar);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);
        editTextAddressTitle = findViewById(R.id.editTextAddressTitle);
        address = findViewById(R.id.address);
        transfereeMobNo = findViewById(R.id.transfereeMobNo);
        transfereeName = findViewById(R.id.transfereeName);
        postalCode = findViewById(R.id.postalCode);
        changeAddress = findViewById(R.id.changeAddress);
        myDialog = new Dialog(this);
        addressList = new ArrayList<>();
        token = retrieveToken();
        transfereeName.setText("sdfsdfsdfsdf");
        customersAddressRead(URLs.ROOT_URL);

        changeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowPopup(addressList);

            }
        });

        directBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!SharedPrefManagerLogin.getInstance(getApplicationContext()).isLoggedIn()) {

                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));

                } else {


                }
            }
        });
        onlinePayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!SharedPrefManagerCustomer.getInstance(getApplicationContext()).isLoggedIn()) {
//
//                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//
////
//                } else {
//                    int customerId = SharedPrefManagerCustomer.getInstance(getApplicationContext()).getUser().getId();
//
//                    try {
//
//
//                        okhttpReqPost(URLs.ROOT_URL, "bankmellat", payIntent, customerId);
//                    } catch (Exception e) {
//                        Toast.makeText(getApplicationContext(), "ارتباط برقرار نشد", Toast.LENGTH_SHORT).show();
//
//                    }
//
//                    startActivity(CustomerInfoIntent);
//
//                }
                Toast.makeText(getApplicationContext(), "فعلا این امکان غیرفعال میباشد", Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void customersAddressRead(String baseURL) {


        progressBar.setVisibility(View.VISIBLE);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        String token1 = "Bearer" + " " + token;

        Api api = mRetrofit.create(Api.class);

        Call<ReqCustomerAllAddress> call = api.customersAddressRead("application/json", token1);

        call.enqueue(new Callback<ReqCustomerAllAddress>() {
            @Override
            public void onResponse(Call<ReqCustomerAllAddress> call, Response<ReqCustomerAllAddress> response) {
                try {


//                    addressList.clear();
//                    for (int i = 0; i <= response.body().getValue().size(); i++) {
//                        CustomersAddress customersAddress = response.body().getValue().get(i);
//
//                        addressList.add(new CustomersAddress(
//                                customersAddress.getId(),
//                                customersAddress.getAreaName(),
//                                customersAddress.getFullLocation(),
//                                customersAddress.getAddress(),
//                                customersAddress.getAreaId(),
//                                customersAddress.getCityId(),
//                                customersAddress.getPostalCode(),
//                                customersAddress.getTitle(),
//                                customersAddress.getTransfereeMobNo(),
//                                customersAddress.getTransfereeName(),
//                                customersAddress.getLat(),
//                                customersAddress.getLng(),
//                                customersAddress.getCityName()
//                        ));
//                    }

//                    setValue();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "درخواست ناموفق، دوباره امتحان کنید", Toast.LENGTH_SHORT).show();

                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ReqCustomerAllAddress> call, Throwable t) {
                try {
                    Toast.makeText(getApplicationContext(), "خطا در اتصال", Toast.LENGTH_SHORT).show();

                } catch (Exception ignored) {

                }

                progressBar.setVisibility(View.GONE);

            }
        });


    }

    private void setValue() {

        editTextAddressTitle.setText(addressList.get(0).getTitle());
        address.setText("sdasdsd");
        transfereeMobNo.setText(addressList.get(0).getTransfereeMobNo());
        transfereeName.setText(addressList.get(0).getTransfereeName());
        postalCode.setText(addressList.get(0).getPostalCode());


    }

    private void customersAddressRead1(String baseURL) {


        progressBar.setVisibility(View.VISIBLE);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        Api api = mRetrofit.create(Api.class);

        Call<ReqCustomerAllAddress> call = api.customersAddressRead("application/json", token);

        call.enqueue(new Callback<ReqCustomerAllAddress>() {
            @Override
            public void onResponse(Call<ReqCustomerAllAddress> call, Response<ReqCustomerAllAddress> response) {

                try {

                    if (response.isSuccessful()) {

                        editTextAddressTitle.setText(response.body().getValue().get(0).getTitle());
                        address.setText(response.body().getValue().get(0).getAddress());
                        transfereeMobNo.setText(response.body().getValue().get(0).getTransfereeMobNo());
                        transfereeName.setText(response.body().getValue().get(0).getTransfereeName());
                        postalCode.setText(response.body().getValue().get(0).getPostalCode());


                    } else {
                        Toast.makeText(getApplicationContext(), response.message().toString(), Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "درخواست ناموفق، دوباره امتحان کنید", Toast.LENGTH_SHORT).show();

                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ReqCustomerAllAddress> call, Throwable t) {
                try {
                    Toast.makeText(getApplicationContext(), "خطا در اتصال", Toast.LENGTH_SHORT).show();

                } catch (Exception ignored) {

                }

                progressBar.setVisibility(View.GONE);

            }
        });


    }


    public void ShowPopup(final List<CustomersAddress> addressList) {


        myDialog.setContentView(R.layout.dialog_select_address);

        RecyclerView recylcerViewCity;
        LinearLayout addAddress = myDialog.findViewById(R.id.addAddress);

        //getting the recyclerview from xml
        recylcerViewCity = myDialog.findViewById(R.id.addressListRecyclerview);
        recylcerViewCity.setHasFixedSize(true);
        recylcerViewCity.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));

        final AddressAdapter.AddressesAdapter citiesAdapter = new AddressAdapter.AddressesAdapter(getApplicationContext(), addressList, new AddressAdapter.ClickListener() {
            @Override
            public void onLongClicked(int position) {


            }

            @Override
            public void onItemlicked(int position) {
                Toast.makeText(PayMethodActivity.this, "شهر به " + addressList.get(position).getTitle() + " تغییر یافت", Toast.LENGTH_LONG).show();
//                saveCity(cityList.get(position));
                myDialog.dismiss();
            }
        });

        recylcerViewCity.setAdapter(citiesAdapter);
        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddAddressActivity.class));
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
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
