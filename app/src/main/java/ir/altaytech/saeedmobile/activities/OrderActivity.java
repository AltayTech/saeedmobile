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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ir.altaytech.saeedmobile.Api;
import ir.altaytech.saeedmobile.Model.Orders;
import ir.altaytech.saeedmobile.Model.ReqOrderList;
import ir.altaytech.saeedmobile.Model.other.LineItems;
import ir.altaytech.saeedmobile.R;
import ir.altaytech.saeedmobile.URLs;
import ir.altaytech.saeedmobile.adapters.OrderAdapter;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class OrderActivity extends AppCompatActivity {

    static List<LineItems> orderItems;
    RecyclerView recylcerView_orders;
    List<Orders> ordersList;
    TextView emptyTextView;
    private ProgressBar progressBar;
    private String status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        emptyTextView = findViewById(R.id.emptyTextView);
        recylcerView_orders = findViewById(R.id.recylcerView_shoppingCart);
        recylcerView_orders.setHasFixedSize(true);
        recylcerView_orders.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        progressBar = findViewById(R.id.progressBar);

        //initializing the productlist
        ordersList = new ArrayList<>();
        orderItems = new ArrayList<>();
        String token = retrieveToken();
//        if (!token.isEmpty()) {
            reqOrderList(URLs.ROOT_URL, token, 2);
//        } else {
//            Toast.makeText(getApplicationContext(), "لطفا وارد شوید", Toast.LENGTH_SHORT).show();
//            Intent loginIntent = new Intent(getApplication(), LoginActivity.class);
//            startActivity(loginIntent);
//        }
    }

    private void reqOrderList(String baseURL, String token, int orderFilter) {
        progressBar.setVisibility(View.VISIBLE);


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        String token1 = "Bearer" + " " + token;


        if (orderFilter == 1) {
            status = "انجام شده";
        } else if (orderFilter == 2) {
            status = "لغو شده";
        } else if (orderFilter == 3) {
            status = "در حال انجام";
        }

        final Api api = mRetrofit.create(Api.class);
        Call<ReqOrderList> call = api.getOrderList(token1, 2);

        call.enqueue(new Callback<ReqOrderList>() {

            @Override
            public void onResponse(Call<ReqOrderList> call, Response<ReqOrderList> response) {
                progressBar.setVisibility(View.GONE);
                Log.i("response", response.message().toString());

                try {

                    for (int i = 0; i < response.body().getValue().size(); i++) {
                        Orders array = response.body().getValue().get(i);
                        try {

                            ordersList.add(new Orders(
                                    array.getId(),
                                    array.getRegDateTime(),
                                    array.getTotal()
                            ));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (ordersList.isEmpty()) {
                        emptyTextView.setText("سفارشی وجود ندارد.");
                    } else {
                        emptyTextView.setEnabled(false);
                        orderAdapter(ordersList);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ReqOrderList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("retrofit error", t.getMessage());
                progressBar.setVisibility(View.GONE);

            }
        });

    }


    private void orderAdapter(final List<Orders> orderlist1) {
        final OrderAdapter.ProductsAdapter adapter = new OrderAdapter.ProductsAdapter(getApplication(), status, orderlist1, new OrderAdapter.ClickListener() {
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

                String itemId = ordersList.get(position).getId();
                clickedItem(itemId);
            }

            @Override
            public void onRemoveItemClicked(int position) {

            }
        });
        try {
            recylcerView_orders.setAdapter(adapter);

        } catch (Exception e) {
            Log.e("adapt error", e.getMessage().toString());
        }

    }

    public void clickedItem(String itemId) {

        saveOrdertId(itemId);
        Intent orderViewIntent = new Intent(getApplication(), OrderViewActivity.class);
        startActivity(orderViewIntent);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

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

    private void saveOrdertId(String orderId) {
        SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("orderIdStorage", MODE_PRIVATE).edit();
        editor.putString("orderId", orderId);
        editor.apply();
    }


    private String retrieveOrderId() {
        String screenIdin;
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("orderIdStorage", MODE_PRIVATE);
        screenIdin = prefs.getString("orderId", ""); //0 is the default value.

        return screenIdin;
    }

}