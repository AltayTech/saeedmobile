package ir.altaytech.saeedmobile.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import ir.altaytech.saeedmobile.Api;
import ir.altaytech.saeedmobile.DatabaseManager;
import ir.altaytech.saeedmobile.Model.AuthenticationToken;
import ir.altaytech.saeedmobile.Model.Customer;
import ir.altaytech.saeedmobile.Model.ReqTransaction;
import ir.altaytech.saeedmobile.Model.ReqWalletBalance;
import ir.altaytech.saeedmobile.Model.Transaction;
import ir.altaytech.saeedmobile.Model.other.WalletTransaction;
import ir.altaytech.saeedmobile.R;
import ir.altaytech.saeedmobile.SharedPrefManagerCustomer;
import ir.altaytech.saeedmobile.URLs;
import ir.altaytech.saeedmobile.adapters.TransactionAdapter;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

//import ir.altaytech.saeedmobile.OAuthInterceptor;


/**
 * Created by Altay on 2/7/2018.
 */

public class TransactionsFragment extends Fragment {

    List<Transaction> transactionList;
    RecyclerView recyclerView;
    View rootView;
    private ProgressBar progressBar;
    private int customerId;
    private String token;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_transactions, container, Boolean.parseBoolean(null));
        recyclerView = rootView.findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));

        progressBar = rootView.findViewById(R.id.progressBar);

        transactionList = new ArrayList<>();

        token = retrieveToken();

        tensactionReq(URLs.ROOT_URL, token);

        return rootView;

    }

    private void tensactionReq(String baseURL, String token) {
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

        Call<ReqTransaction> call = api.getTransactions("application/json", token1);

        call.enqueue(new Callback<ReqTransaction>() {
            @Override
            public void onResponse(Call<ReqTransaction> call, Response<ReqTransaction> response) {

                try {

                    Log.i("productM", response.message());

                    Log.i("productM", String.valueOf(response.body().getValue().size()));


                    if (response.isSuccessful()) {

                        transactionList.clear();

                        for (int i = 0; i < response.body().getValue().size(); i++) {
                            Transaction array = response.body().getValue().get(i);

                            try {
                                transactionList.add(new Transaction(
                                        array.getRegDateTime(),
                                        array.getPreviousBalance(),
                                        array.getDeposit(),
                                        array.getWithdraw(),
                                        array.getBalance(),
                                        array.getOrderId(),
                                        array.getFinancialTransactionTypes()
                                ));

                            } catch (Exception e) {
//                            Log.d("retrofit error", e.getMessage());

                            }
                        }


                        transactionAdapter();


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
            public void onFailure(Call<ReqTransaction> call, Throwable t) {
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


    private void transactionAdapter() {
        final TransactionAdapter.TransactionsAdapter adapter = new TransactionAdapter.TransactionsAdapter(getActivity(), transactionList, new TransactionAdapter.ClickListener() {


            @Override
            public void onLongClicked(int position) {

            }


            @Override
            public void onItemlicked(int position) {

//                Toast.makeText(getContext(), " onItemlicked button clicked ", Toast.LENGTH_SHORT).show();
//                clickedItem(position);

            }


        });
        recyclerView.setAdapter(adapter);
    }

    private String retrieveToken() {
        String token;
        SharedPreferences prefs = getContext().getSharedPreferences("paramValue", MODE_PRIVATE);
        token = prefs.getString("param", null); //0 is the default value.
        return token;
    }
}