package ir.altaytech.saeedmobile.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ir.altaytech.saeedmobile.Api;
import ir.altaytech.saeedmobile.Model.Order;
import ir.altaytech.saeedmobile.Model.OrderDetail;
import ir.altaytech.saeedmobile.Model.ReqOrder;
import ir.altaytech.saeedmobile.URLs;
import ir.altaytech.saeedmobile.fragments.ProductViewFragment;
import ir.altaytech.saeedmobile.R;
import ir.altaytech.saeedmobile.adapters.OrderViewAdapter;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class OrderViewActivity extends AppCompatActivity {
    private ProgressBar progressBar;

    List<OrderDetail> orderItemsList;

    RecyclerView recylcerView_orderView;

    private Order order;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_view);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        progressBar = findViewById(R.id.progressBar);

        recylcerView_orderView = findViewById(R.id.recylcerView_orderView);
        recylcerView_orderView.setHasFixedSize(true);
        recylcerView_orderView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        orderItemsList = new ArrayList<>();

        reqOrder(URLs.ROOT_URL, retrieveToken(), retrieveOrderId());


    }


    private void reqOrder(String baseURL, String token, String orderId) {
        progressBar.setVisibility(View.VISIBLE);


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        String token1 = "Bearer" + " " + token;

        final Api api = mRetrofit.create(Api.class);
        Call<ReqOrder> call = api.getOrder(token1, orderId);

        call.enqueue(new Callback<ReqOrder>() {

            @Override
            public void onResponse(Call<ReqOrder> call, Response<ReqOrder> response) {
                progressBar.setVisibility(View.GONE);
                Log.i("response", response.message().toString());

                try {

                    order = response.body().getValue();
                    for (int i = 0; i < order.getOrderDetails().size(); i++) {
                        OrderDetail array = order.getOrderDetails().get(i);

                        try {

                            orderItemsList.add(new OrderDetail(
                                    array.getProductName(),
                                    array.getPrice(),
                                    array.getDiscountPercent(),
                                    array.getFinalPrice(),
                                    array.getQuantity(),
                                    array.getProductUnit()
                            ));

                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
                orderViewAdapter(orderItemsList);


            }

            @Override
            public void onFailure(Call<ReqOrder> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("retrofit error", t.getMessage());
                progressBar.setVisibility(View.GONE);

            }
        });

    }


    private void orderViewAdapter(List<OrderDetail> itemList) {
        final OrderViewAdapter.ProductsAdapter adapter = new OrderViewAdapter.ProductsAdapter(getApplication(), itemList, new OrderViewAdapter.ClickListener() {

            @Override
            public void onDecreaseButtonClicked(int position) {


            }

            @Override
            public void onLongClicked(int position) {

            }

            @Override
            public void onIncreaseCartClicked(int position) {

            }

            @Override
            public void onItemclicked(int position) {


            }

            @Override
            public void onRemoveItemClicked(int position) {

            }
        });

        recylcerView_orderView.setAdapter(adapter);


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

    private String retrieveOrderId() {
        String screenIdin;
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("orderIdStorage", MODE_PRIVATE);
        screenIdin = prefs.getString("orderId", ""); //0 is the default value.

        return screenIdin;
    }
}