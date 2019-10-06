package ir.altaytech.saeedmobile.fragments;


import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.concurrent.TimeUnit;

import ir.altaytech.saeedmobile.Api;
import ir.altaytech.saeedmobile.DatabaseManager;
import ir.altaytech.saeedmobile.Model.Product;
import ir.altaytech.saeedmobile.Model.ReqProduct;
import ir.altaytech.saeedmobile.Model.ReqShoppingCard;
import ir.altaytech.saeedmobile.Model.ShoppingCard;
import ir.altaytech.saeedmobile.R;
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

public class ProductViewFragment extends Fragment {

    ProgressBar progressBar;


    private static Product product;
    ImageView imageView;
    TextView textViewShortDesc;
    TextView textViewPrice;
    TextView textViewTitle;
    private View rootView;
    private Button favButton;
    private Button shoppingCartButton;

    int productId;
    private TextView textViewIsAvailable;

    public static void setProduct(Product product1) {
        product = product1;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_product_view, container, Boolean.parseBoolean(null));

        progressBar = rootView.findViewById(R.id.progressBar);
        imageView = rootView.findViewById(R.id.imageView);
        textViewTitle = rootView.findViewById(R.id.textViewTitle);
        textViewShortDesc = rootView.findViewById(R.id.textViewShortDesc);
        textViewPrice = rootView.findViewById(R.id.textViewPrice);
        textViewIsAvailable = rootView.findViewById(R.id.textViewIsAvailable);


        productId = retrieveProductId();
        productReq(URLs.ROOT_URL, productId);

        favButton = rootView.findViewById(R.id.likeButtonproductview);
        shoppingCartButton = rootView.findViewById(R.id.addToShoppingCardproductview);

        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemToFavorite();
            }
        });

        shoppingCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addShoppingCartReq(URLs.ROOT_URL, productId);

            }
        });

        return rootView;
    }

    private void productReq(String baseURL, int productId) {
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


        Api api = mRetrofit.create(Api.class);

        Call<ReqProduct> call = api.getProduct(productId);

        call.enqueue(new Callback<ReqProduct>() {
            @Override
            public void onResponse(Call<ReqProduct> call, Response<ReqProduct> response) {
                Log.i("productM", response.message());

                Log.i("productM", response.body().getValue().toString());


                try {

                    product = response.body().getValue();

                    infoAdapter(product);

                } catch (Exception e) {
                    e.printStackTrace();

                    try {
                        Toast.makeText(getContext(), "خطا در اتصال2", Toast.LENGTH_SHORT).show();

                    } catch (Exception ignored) {

                    }

                }
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ReqProduct> call, Throwable t) {
                try {

                    Log.i("retrofiterror", t.getMessage().toString());

                    Toast.makeText(getContext(), "خطا در اتصال3", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                progressBar.setVisibility(View.GONE);

            }
        });


    }

    private void infoAdapter(Product product) {

        Glide.with(getContext())
                .load(product.getPicPath())
                .into(imageView);

        NumberFormat nf = new DecimalFormat("#.####");
        textViewTitle.setText(product.getName());
        textViewPrice.setText(String.valueOf(nf.format(product.getPrice())));
        textViewShortDesc.setText(product.getDescription());

        if (product.getAvailable()) {
            textViewIsAvailable.setText("موجود است");
            textViewIsAvailable.setTextColor(Color.parseColor("#FF4CAF50"));

        } else {
            textViewIsAvailable.setText("موجود نیست");
            textViewIsAvailable.setTextColor(Color.parseColor("#FFC62828"));

        }

    }


    private void addItemToFavorite() {

        int id = product.getId();
        boolean isExist = DatabaseManager.productIsExistFavorite(id, getContext());
        if (isExist) {
            Toast.makeText(getContext(), "در لیست موارد مورد علاقه موجود است", Toast.LENGTH_SHORT).show();

        } else {
            DatabaseManager.insertRowFavorite(product);
            Toast.makeText(getContext(), "به لیست موارد مورد علاقه موجود است", Toast.LENGTH_SHORT).show();

        }
    }

    private int retrieveProductId() {
        int screenIdin;
        SharedPreferences prefs = getContext().getSharedPreferences("productIdStorage", MODE_PRIVATE);
        screenIdin = prefs.getInt("productId", 18); //0 is the default value.

        return screenIdin;
    }


    private void addShoppingCartReq(String baseURL, int productId) {


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

        call.enqueue(new Callback<ReqShoppingCard>() {
            @Override
            public void onResponse(Call<ReqShoppingCard> call, Response<ReqShoppingCard> response) {
                try {
                    if (response.body().getSuccess()) {
                        saveHashCardId(response.body().getValue().getHashCartId());
                        Toast.makeText(getContext(), "به سبد خرید اضافه شد", Toast.LENGTH_SHORT).show();

                        Log.i("token", response.body().getSuccess().toString());
                        Log.i("token", response.message());
                    } else {

                        saveHashCardId("");

                    }
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


}