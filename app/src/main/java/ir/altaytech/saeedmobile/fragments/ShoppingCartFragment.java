package ir.altaytech.saeedmobile.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ir.altaytech.saeedmobile.Api;
import ir.altaytech.saeedmobile.Model.ReqShoppingCard;
import ir.altaytech.saeedmobile.Model.ShoppingCard;
import ir.altaytech.saeedmobile.Model.ShoppingCardProduct;
import ir.altaytech.saeedmobile.R;
import ir.altaytech.saeedmobile.SharedPrefManagerCustomer;
import ir.altaytech.saeedmobile.SharedPrefManagerLogin;
import ir.altaytech.saeedmobile.URLs;
import ir.altaytech.saeedmobile.activities.LoginActivity;
import ir.altaytech.saeedmobile.activities.PayMethodActivity;
import ir.altaytech.saeedmobile.adapters.ProductAdapterShoppingCart;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Altay on 2/7/2018.
 */

public class ShoppingCartFragment extends Fragment {
    ProgressBar progressBar;


    RecyclerView recylcerView_shoppingCart;
    List<ShoppingCardProduct> productList;

    View rootView;

    private Button orderButtons;
    private TextView totalPriceTextView;
    private TextView productTypeNumberNumber;
    private TextView productNumberNumber;
    private int productTypeNumber;
    private int finalSubTotalAmount;
    private int cartDetailsCount;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_shopping_cart, container, Boolean.parseBoolean(null));

        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/far_traffic.ttf");
        TextView totalPriceTextViewText = rootView.findViewById(R.id.totalPriceTextViewText);
        TextView productNumberNumberText = rootView.findViewById(R.id.productNumberNumberText);
        TextView productTypeNumberNumberText = rootView.findViewById(R.id.productTypeNumberNumberText);

        totalPriceTextViewText.setTypeface(myTypeface);
        productNumberNumberText.setTypeface(myTypeface);
        productTypeNumberNumberText.setTypeface(myTypeface);

//      getting the recyclerview from xml
        recylcerView_shoppingCart = rootView.findViewById(R.id.recylcerView_shoppingCart);
        recylcerView_shoppingCart.setHasFixedSize(true);
        recylcerView_shoppingCart.setLayoutManager(new GridLayoutManager(getContext(), 1));

        productList = new ArrayList<>();
        progressBar = rootView.findViewById(R.id.progressBar);

        orderButtons = rootView.findViewById(R.id.orderButtonS);


        totalPriceTextView = rootView.findViewById(R.id.totalPriceTextView);
        productTypeNumberNumber = rootView.findViewById(R.id.productTypeNumberNumber);
        productNumberNumber = rootView.findViewById(R.id.productNumberNumber);
        totalPriceTextView.setTypeface(myTypeface);
        productTypeNumberNumber.setTypeface(myTypeface);
        productNumberNumber.setTypeface(myTypeface);

        String hashCartId = retrieveHashCardId();
        if (!hashCartId.equals("")) {
            readShoppingCartItemsReq(URLs.ROOT_URL);
        } else {
            Toast.makeText(getContext(), "سبد خرید خالی است", Toast.LENGTH_SHORT).show();

        }

        orderButtons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!SharedPrefManagerLogin.getInstance(getContext()).isLoggedIn()) {
                    startActivity(new Intent(getContext(), LoginActivity.class));

                } else {
                    try {

                        startActivity(new Intent(getContext(), PayMethodActivity.class));
                    } catch (Exception e) {

                    }
                }
            }
        });


        return rootView;

    }

    private void readShoppingCartItemsReq(String baseURL) {


        progressBar.setVisibility(View.VISIBLE);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        String hashCartId = retrieveHashCardId();

        Api api = mRetrofit.create(Api.class);
        Call<ReqShoppingCard> call = api.readCartItems("application/json", hashCartId);


        call.enqueue(new Callback<ReqShoppingCard>() {
            @Override
            public void onResponse(Call<ReqShoppingCard> call, Response<ReqShoppingCard> response) {


                try {
                    if (response.body().getSuccess()) {

                        saveHashCardId(response.body().getValue().getHashCartId());

                        try {
                            productList.clear();

                            for (int i = 0; i < response.body().getValue().getCartInfo().getCartDetails().size(); i++) {
                                ShoppingCardProduct array = response.body().getValue().getCartInfo().getCartDetails().get(i);

                                try {
                                    productList.add(new ShoppingCardProduct(
                                            array.getProductId(),
                                            array.getProductName(),
                                            array.getPrice(),
                                            array.getDiscountPercent(),
                                            array.getFinalPrice(),
                                            array.getQuantity(),
                                            array.getProductPicPath(),
                                            array.getUnit(),
                                            array.getWeight()

                                    ));

                                } catch (Exception e) {
//                            Log.d("retrofit error", e.getMessage());

                                }
                            }
                            finalSubTotalAmount = response.body().getValue().getCartInfo().getFinalSubTotalAmount();
                            cartDetailsCount = response.body().getValue().getCartInfo().getCartDetailsCount();

                            setNumbersTV(finalSubTotalAmount, cartDetailsCount);
//

                        } catch (Exception e) {
                            try {
                                Toast.makeText(getContext(), "خطا در اتصال", Toast.LENGTH_SHORT).show();

                            } catch (Exception ignored) {

                            }

                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    saveHashCardId("");
                }
                progressBar.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<ReqShoppingCard> call, Throwable t) {
                try {
                    Toast.makeText(getContext(), "خطا در اتصال", Toast.LENGTH_SHORT).show();

                } catch (Exception ignored) {

                }

                progressBar.setVisibility(View.GONE);

            }
        });


    }


    private void changeShoppingCartReq(String baseURL, int productId, final boolean add) {


        progressBar.setVisibility(View.VISIBLE);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        String hashCartId = retrieveHashCardId();

        ShoppingCard shoppingCard;
        shoppingCard = new ShoppingCard(hashCartId, productId);

        Api api = mRetrofit.create(Api.class);
        Call<ReqShoppingCard> call = api.increaseCartItem("application/json", shoppingCard);

        if (add) {

            call = api.increaseCartItem("application/json", shoppingCard);

        } else {

            call = api.decreaseCartItem("application/json", shoppingCard);

        }

        call.enqueue(new Callback<ReqShoppingCard>() {
            @Override
            public void onResponse(Call<ReqShoppingCard> call, Response<ReqShoppingCard> response) {


                try {

                    if (response.body().getSuccess()) {

                        saveHashCardId(response.body().getValue().getHashCartId());

                        if (add) {
                            Toast.makeText(getContext(), "به سبد خرید اضافه شد", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getContext(), "از سبد خرید کم شد", Toast.LENGTH_SHORT).show();

                        }

                        try {
                            productList.clear();

                            for (int i = 0; i < response.body().getValue().getCartInfo().getCartDetails().size(); i++) {
                                ShoppingCardProduct array = response.body().getValue().getCartInfo().getCartDetails().get(i);

                                try {
                                    productList.add(new ShoppingCardProduct(
                                            array.getProductId(),
                                            array.getProductName(),
                                            array.getPrice(),
                                            array.getDiscountPercent(),
                                            array.getFinalPrice(),
                                            array.getQuantity(),
                                            array.getProductPicPath(),
                                            array.getUnit(),
                                            array.getWeight()

                                    ));

                                } catch (Exception e) {
//                            Log.d("retrofit error", e.getMessage());

                                }
                            }
                            finalSubTotalAmount = response.body().getValue().getCartInfo().getFinalSubTotalAmount();
                            cartDetailsCount = response.body().getValue().getCartInfo().getCartDetailsCount();

                            setNumbersTV(finalSubTotalAmount, cartDetailsCount);
//

                        } catch (Exception e) {
                            try {
                                Toast.makeText(getContext(), "خطا در اتصال", Toast.LENGTH_SHORT).show();

                            } catch (Exception ignored) {

                            }

                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    saveHashCardId("");

                }
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ReqShoppingCard> call, Throwable t) {
                try {
                    Toast.makeText(getContext(), "خطا در اتصال", Toast.LENGTH_SHORT).show();

                } catch (Exception ignored) {

                }

                progressBar.setVisibility(View.GONE);

            }
        });


    }

    private void removeShoppingCartItemReq(String baseURL, int productId) {


        progressBar.setVisibility(View.VISIBLE);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        String hashCartId = retrieveHashCardId();

        Api api = mRetrofit.create(Api.class);
        Call<ReqShoppingCard> call = api.removeCartItem("application/json", hashCartId, productId);


        call.enqueue(new Callback<ReqShoppingCard>() {
            @Override
            public void onResponse(Call<ReqShoppingCard> call, Response<ReqShoppingCard> response) {

                try {

                    if (response.body().getSuccess()) {

                        saveHashCardId(response.body().getValue().getHashCartId());


                        Toast.makeText(getContext(), "از سبد خرید حذف شد", Toast.LENGTH_SHORT).show();

                        Log.i("token", response.body().getSuccess().toString());
                        Log.i("token", response.message());

                    }

                    readShoppingCartItemsReq(URLs.ROOT_URL);

                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                    saveHashCardId("");

                }


                progressBar.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<ReqShoppingCard> call, Throwable t) {
                try {
                    Toast.makeText(getContext(), "خطا در اتصال", Toast.LENGTH_SHORT).show();

                } catch (Exception ignored) {

                }

                progressBar.setVisibility(View.GONE);

            }
        });


    }


    private void saveHashCardId(String hashCardId) {
        SharedPreferences.Editor editor = getContext().getSharedPreferences("hashCardIdStorage", MODE_PRIVATE).edit();
        editor.putString("hashCardId", hashCardId);
        editor.apply();
    }

    private String retrieveHashCardId() {
        String hashCardId;
        SharedPreferences prefs = getContext().getSharedPreferences("hashCardIdStorage", MODE_PRIVATE);
        hashCardId = prefs.getString("hashCardId", ""); //0 is the default value.

        return hashCardId;
    }


    private void productAdapter(final List<ShoppingCardProduct> productList) {
        final ProductAdapterShoppingCart.ProductsAdapter adapter = new ProductAdapterShoppingCart.ProductsAdapter(getActivity(), productList, new ProductAdapterShoppingCart.ClickListener() {

            @Override
            public void onDecreaseButtonClicked(int position) {
                changeShoppingCartReq(URLs.ROOT_URL, productList.get(position).getProductId(), false);
            }

            @Override
            public void onLongClicked(int position) {

            }

            @Override
            public void onIncreaseCartClicked(int position) {
                changeShoppingCartReq(URLs.ROOT_URL, productList.get(position).getProductId(), true);

            }

            @Override
            public void onItemclicked(int position) {


            }

            @Override
            public void onRemoveItemClicked(int position) {

                removeShoppingCartItemReq(URLs.ROOT_URL, productList.get(position).getProductId());


            }
        });
        try {
            recylcerView_shoppingCart.setAdapter(adapter);

        } catch (Exception e) {
//            Log.e("adapt error", e.getMessage());
        }
    }

    public void setNumbersTV(int FinalSubTotalAmount, int productNumber) {

        productAdapter(productList);

        productTypeNumber = productList.size();
        productTypeNumberNumber.setText(String.valueOf(productTypeNumber));


        totalPriceTextView.setText(String.valueOf(FinalSubTotalAmount));
        productNumberNumber.setText(String.valueOf(productNumber));

    }

}