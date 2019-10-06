package ir.altaytech.saeedmobile.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import ir.altaytech.saeedmobile.Api;
import ir.altaytech.saeedmobile.Model.FilteredRequest;
import ir.altaytech.saeedmobile.Model.Product;
import ir.altaytech.saeedmobile.Model.ReqFilterResponse;
import ir.altaytech.saeedmobile.Model.other.Product2;
import ir.altaytech.saeedmobile.R;
import ir.altaytech.saeedmobile.URLs;
import ir.altaytech.saeedmobile.adapters.ProductAdapterSearch;
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

public class ProductSearchFragment extends Fragment {

    List<Product2> productList;
    RecyclerView recyclerView;

    Button searchButton;
    TextView searchText;
    private CharSequence searchKeyValue;
    private ProgressBar progressBar;
    private View rootView;
    private ProductViewFragment productViewFragment;
    private List<Product> searchProducts;
    private int itemId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_product_search, container, Boolean.parseBoolean(null));

        //getting the recyclerview from xml
        recyclerView = rootView.findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        progressBar = rootView.findViewById(R.id.progressBar);

        //initializing the productlist
        productList = new ArrayList<>();
        searchProducts = new ArrayList<>();
        searchButton = rootView.findViewById(R.id.searchButton);
        searchText = rootView.findViewById(R.id.searchTextSearchFragment);

        productViewFragment = new ProductViewFragment();
        searhAction();

        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searhAction();
                    return true;
                }
                return false;
            }
        });
        return rootView;

    }

    private void searhAction() {
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CharSequence werwerew = searchText.getText();
                searchKeyValue = werwerew.toString();

                Toast.makeText(getContext(), "This is my Toast message!", Toast.LENGTH_SHORT).show();
                searchReq(URLs.ROOT_URL, (String) searchKeyValue, 1, 0, 1000, true, 1);
            }
        });
    }

    //      private void searchReq(String baseURL, String SearchValue) {
//        progressBar.setVisibility(View.VISIBLE);
//
//
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .connectTimeout(20, TimeUnit.SECONDS)
//                .writeTimeout(20, TimeUnit.SECONDS)
//                .readTimeout(30, TimeUnit.SECONDS)
//                .addInterceptor(interceptor)
//                .build();
//
//        Retrofit mRetrofit = new Retrofit.Builder()
//                .baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
//                .client(okHttpClient)
//                .build();
//
//
//        Api api = mRetrofit.create(Api.class);
//
//        Call<ReqSearch> call = api.searchReq(SearchValue);
//
//        call.enqueue(new Callback<ReqSearch>() {
//            @Override
//            public void onResponse(Call<ReqSearch> call, Response<ReqSearch> response) {
//                Log.i("productM", response.message());
//
//                Log.i("productM", response.body().getValue().toString());
//
//
//                try {
//
//                    searchProducts.clear();
//
//                    for (int i = 0; i < response.body().getValue().getProducts().size(); i++) {
//                        Property array = response.body().getValue().getProducts().get(i);
//
//                        try {
//                            searchProducts.add(new Property(
//                                    array.getId(),
//                                    array.getTitle()
//                            ));
//
//                        } catch (Exception e) {
////                            Log.d("retrofit error", e.getMessage());
//
//                        }
//                    }
//                    productAdapter(searchProducts);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//
//                    try {
//                        Toast.makeText(getContext(), "خطا در اتصال2", Toast.LENGTH_SHORT).show();
//
//                    } catch (Exception ignored) {
//
//                    }
//
//                }
//                progressBar.setVisibility(View.GONE);
//
//            }
//
//            @Override
//            public void onFailure(Call<ReqSearch> call, Throwable t) {
//                try {
//
//                    Log.i("retrofiterror", t.getMessage().toString());
//
//                    Toast.makeText(getContext(), "خطا در اتصال3", Toast.LENGTH_SHORT).show();
//                    t.printStackTrace();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                progressBar.setVisibility(View.GONE);
//
//            }
//        });
//
//
//    }
    private void searchReq(String baseURL, String SearchValue, int orderBy, int fromPriceRange, int toPriceRAnge, Boolean isAvailable, int page) {
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

        FilteredRequest filteredRequest;
        filteredRequest = new FilteredRequest(SearchValue, 1);

        Api api = mRetrofit.create(Api.class);

//        Call<ReqFilterResponse> call = api.filterSearch("application/json", SearchValue, orderBy, fromPriceRange, toPriceRAnge, isAvailable, page);
        Call<ReqFilterResponse> call = api.filterSearch("application/json", filteredRequest);

        call.enqueue(new Callback<ReqFilterResponse>() {
            @Override
            public void onResponse(Call<ReqFilterResponse> call, Response<ReqFilterResponse> response) {

                Log.i("productM", response.message());
                Log.i("productM", response.raw().toString());
                Log.i("productM", response.message());
                Log.i("productM", response.message());
                Log.i("productM", response.message());

                Log.i("productM", response.body().getValue().toString());

                try {

                    searchProducts.clear();

                    for (int i = 0; i < response.body().getValue().getFilteredProducts().size(); i++) {
                        Product array = response.body().getValue().getFilteredProducts().get(i);

                        try {
                            searchProducts.add(new Product(
                                    array.getId(),
                                    array.getName(),
                                    array.getShortName(),
                                    array.getDiscountPercent(),
                                    array.getPrice(),
                                    array.getFinalPrice(),
                                    array.getPicPath()
                            ));

                        } catch (Exception e) {
                            e.printStackTrace();
//                            Log.d("retrofit error", e.getMessage());

                        }
                    }
                    productAdapter(searchProducts);

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
            public void onFailure(Call<ReqFilterResponse> call, Throwable t) {
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

    private void productAdapter(List<Product> productList) {
        final ProductAdapterSearch.ProductsAdapter adapter = new ProductAdapterSearch.ProductsAdapter(getActivity(), productList, new ProductAdapterSearch.ClickListener() {


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

                itemId = searchProducts.get(position).getId();

                clickedItem(itemId);

            }

            @Override
            public void onRemoveItemClicked(int position) {

            }
        });
        recyclerView.setAdapter(adapter);
    }


    public void clickedItem(int itemId) {
        saveProductId(itemId);
        changeFragment(productViewFragment);
    }

    private void saveProductId(int productId) {
        SharedPreferences.Editor editor = getContext().getSharedPreferences("productIdStorage", MODE_PRIVATE).edit();
        editor.putInt("productId", productId);
        editor.apply();
    }

    private void changeFragment(Fragment targetFragment) {

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

    }

}