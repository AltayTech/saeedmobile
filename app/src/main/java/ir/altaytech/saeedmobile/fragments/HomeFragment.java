package ir.altaytech.saeedmobile.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import ir.altaytech.saeedmobile.Api;
import ir.altaytech.saeedmobile.DatabaseManager;
import ir.altaytech.saeedmobile.Model.AmazingProduct;
import ir.altaytech.saeedmobile.Model.Group;
import ir.altaytech.saeedmobile.Model.ReqMainPage;
import ir.altaytech.saeedmobile.Model.other.Product2;
import ir.altaytech.saeedmobile.Model.Product;
import ir.altaytech.saeedmobile.Model.Slider;
import ir.altaytech.saeedmobile.R;
import ir.altaytech.saeedmobile.SharedPrefManagerLogin;
import ir.altaytech.saeedmobile.URLs;
import ir.altaytech.saeedmobile.adapters.AmazingProductAdapter;
import ir.altaytech.saeedmobile.adapters.ProductAdapter_category;
import ir.altaytech.saeedmobile.adapters.PromotedProductAdapter;
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

public class HomeFragment extends Fragment {
    View rootView;
    RecyclerView recylcerViewCategory;
    RecyclerView recylcerViewAmazingProduct;
    private RecyclerView recylcerViewPromotedProduct;
    private ProgressBar progressBar;

    List<Group> groupList;
    List<Slider> sliderList;
    List<AmazingProduct> amazingProductList;
    List<Product> promotedProductList1;


    private String token;


    private static int itemPosition;


    SliderLayout mSlider;

    private ProductViewFragment productViewFragment;
    private int itemId;


    public static int getItemPosition() {
        return itemPosition;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_home, container, Boolean.parseBoolean(null));
        mSlider = rootView.findViewById(R.id.slider);
        progressBar = rootView.findViewById(R.id.progressBar);

        //getting the recyclerview from xml
        recylcerViewCategory = rootView.findViewById(R.id.recylcerViewCategory);
        recylcerViewCategory.setHasFixedSize(true);
        recylcerViewCategory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        recylcerViewAmazingProduct = rootView.findViewById(R.id.recylcerViewAmazingProduct);
        recylcerViewAmazingProduct.setHasFixedSize(true);
        recylcerViewAmazingProduct.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        recylcerViewPromotedProduct = rootView.findViewById(R.id.recylcerViewPromotedProduct);
        recylcerViewPromotedProduct.setHasFixedSize(true);
        recylcerViewPromotedProduct.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        sliderList = new ArrayList<>();
        amazingProductList = new ArrayList<>();
        promotedProductList1 = new ArrayList<>();
        groupList = new ArrayList<>();


        productViewFragment = new ProductViewFragment();

        if (!SharedPrefManagerLogin.getInstance(getContext()).isLoggedIn()) {
            token = retrieveToken();

        } else {
            token = "";
        }
        mainPageReq(URLs.ROOT_URL);

        DatabaseManager.openDatabase(getContext());


        return rootView;

    }

    private void changeFragment(Fragment targetFragment) {

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

    }

    private void mainPageReq(String baseURL) {
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

        Call<ReqMainPage> call = api.getMainPage();

        call.enqueue(new Callback<ReqMainPage>() {
            @Override
            public void onResponse(Call<ReqMainPage> call, Response<ReqMainPage> response) {
                Log.i("productM", response.message());

                Log.i("productM", response.body().getValue().toString());


                try {

                    sliderList.clear();

                    for (int i = 0; i < response.body().getValue().getSliders().size(); i++) {
                        Slider array = response.body().getValue().getSliders().get(i);

                        try {
                            sliderList.add(new Slider(
                                    array.getId(),
                                    array.getInx(),
                                    array.getTitle(),
                                    array.getAlt(),
                                    array.getPicPath()
                            ));

                        } catch (Exception e) {
//                            Log.d("retrofit error", e.getMessage());

                        }
                    }
                    sliderAdapter(sliderList);

                    groupList.clear();
                    for (int i = 0; i < response.body().getValue().getGroups().size(); i++) {
                        Group array = response.body().getValue().getGroups().get(i);

                        try {
                            groupList.add(new Group(
                                    array.getId(),
                                    array.getTitle(),
                                    array.getGroupPicPath(),
                                    array.getSubGroups()
                            ));

                        } catch (Exception e) {
//                            Log.d("retrofit error", e.getMessage());

                        }
                    }

                    productGroupAdapter(groupList);


                    amazingProductList.clear();
                    for (int i = 0; i < response.body().getValue().getAmazingOffers().size(); i++) {
                        AmazingProduct array = response.body().getValue().getAmazingOffers().get(i);
                        Log.i("AmazingProduct", array.getPicPath());

                        try {
                            amazingProductList.add(new AmazingProduct(
                                    array.getId(),
                                    array.getPrice(),
                                    array.getAvailable(),
                                    array.getDiscountPercent(),
                                    array.getFinalPrice(),
                                    array.getPicPath(),
                                    array.getProductId(),
                                    array.getProductName(),
                                    array.getDescription(),
                                    array.getDetails(),
                                    array.getShortName(),
                                    array.getGuaranteeDescription()
                            ));

                        } catch (Exception e) {
//                            Log.d("retrofit error", e.getMessage());

                        }
                    }

                    amazingProductAdapter();

                    Log.i("promotedProductList1", String.valueOf(response.body().getValue().getBrands().size()));


                    promotedProductList1.clear();
                    for (int i = 0; i < response.body().getValue().getPromoSectionProducts().get(0).getPromProducts().size(); i++) {
                        Product array = response.body().getValue().getPromoSectionProducts().get(0).getPromProducts().get(i);
                        Log.i("promotedProductList1", array.getPicPath());

                        try {
                            promotedProductList1.add(new Product(
                                    array.getId(),
                                    array.getDescription(),
                                    array.getName(),
                                    array.getDiscountPercent(),
                                    array.getPrice(),
                                    array.getFinalPrice(),
                                    array.getPicPath()

                            ));

                        } catch (Exception e) {
//                            Log.d("retrofit error", e.getMessage());

                        }
                    }

                    promotedProductAdapter(promotedProductList1, response.body().getValue().getPromoSectionProducts().get(0).getTitle());

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
            public void onFailure(Call<ReqMainPage> call, Throwable t) {
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


    private void sliderAdapter(List<Slider> sliderList) {

        mSlider.removeAllSliders();
        for (int i = 0; i < sliderList.size(); i++) {


            TextSliderView textSliderView = new TextSliderView(getContext());
            textSliderView
                    .description(sliderList.get(i).getTitle())
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .image(sliderList.get(i).getPicPath().trim())
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {

//                            clickedFeaturedItem(mSlider.getCurrentPosition());
                        }
                    });


            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("foot", "sdfsdfdsf");

            mSlider.addSlider(textSliderView);
        }

        mSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mSlider.setCustomAnimation(new DescriptionAnimation());
        mSlider.setDuration(4000);

    }

    private void productGroupAdapter(List<Group> groups) {

        final ProductAdapter_category.ProductsAdapter productCategoryAdapter = new ProductAdapter_category.ProductsAdapter(getActivity(), groupList, new ProductAdapter_category.ClickListener() {


            @Override
            public void onLongClicked(int position) {

            }


            @Override
            public void onItemlicked(int position) {

            }
        });
        recylcerViewCategory.setAdapter(productCategoryAdapter);

    }

    private void amazingProductAdapter() {
        final AmazingProductAdapter.AmazingProductsAdapter adapter = new AmazingProductAdapter.AmazingProductsAdapter(getActivity(), amazingProductList, new AmazingProductAdapter.ClickListener() {

            @Override
            public void onFavButtonClicked(int position) {
                addItemToFavorite(position);
                itemPosition = position;
            }

            @Override
            public void onLongClicked(int position) {

            }

            @Override
            public void onShoppingCartClicked(int position) {

            }

            @Override
            public void onItemlicked(int position) {

                itemId = amazingProductList.get(position).getProductId();

                clickedItem(itemId);

            }


        });
        recylcerViewAmazingProduct.setAdapter(adapter);
    }

    private void promotedProductAdapter(final List<Product> promotedProduct, String promotedSectionTitle) {
        TextView promotedProductTextview = rootView.findViewById(R.id.promotedProductTextview);
        promotedProductTextview.setText(promotedSectionTitle);
        TextView morePromotedProductTextview = rootView.findViewById(R.id.morePromotedProductTextview);
        morePromotedProductTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "لیست کامل", Toast.LENGTH_SHORT).show();

            }
        });

        final PromotedProductAdapter.PromotedProductsAdapter adapter = new PromotedProductAdapter.PromotedProductsAdapter(getActivity(), promotedProduct, new PromotedProductAdapter.ClickListener() {

            @Override
            public void onFavButtonClicked(int position) {
                addItemToFavorite(position);
                itemPosition = position;
            }

            @Override
            public void onLongClicked(int position) {

            }

            @Override
            public void onShoppingCartClicked(int position) {
            }

            @Override
            public void onItemlicked(int position) {

                itemId = promotedProduct.get(position).getId();

                clickedItem(itemId);

            }


        });
        recylcerViewPromotedProduct.setAdapter(adapter);
    }

    @Override
    public void onStop() {
        mSlider.stopAutoCycle();

        super.onStop();
    }


    private void saveStatus(int starter, int itemPosition) {
        SharedPreferences.Editor editor = getContext().getSharedPreferences("StatusStorage", MODE_PRIVATE).edit();
        editor.putInt("starter", starter);
        editor.putInt("itemPosition", itemPosition);
        editor.apply();
    }

    private int retrieveStatus() {
        int screenIdin;
        SharedPreferences prefs = getContext().getSharedPreferences("StatusStorage", MODE_PRIVATE);
        screenIdin = prefs.getInt("starter", 1); //0 is the default value.

        return screenIdin;
    }

    private void addItemToFavorite(int position) {

        int id = promotedProductList1.get(position).getId();
        boolean isExist = DatabaseManager.productIsExistFavorite(id, getContext());
        if (isExist) {
            Toast.makeText(getContext(), "در لیست موارد مورد علاقه موجود است", Toast.LENGTH_SHORT).show();

        } else {
//            DatabaseManager.insertRowFavorite(promotedProductList1.get(position));
            Toast.makeText(getContext(), "به موارد مورد علاقه اضافه گردید", Toast.LENGTH_SHORT).show();

        }
    }

    public void clickedItem(int itemId) {
        saveProductId(itemId);
        changeFragment(productViewFragment);
    }


    private String retrieveToken() {
        String token;
        SharedPreferences prefs = getContext().getSharedPreferences("paramValue", MODE_PRIVATE);
        token = prefs.getString("param", null); //0 is the default value.
        return token;
    }


    private void saveProductId(int productId) {
        SharedPreferences.Editor editor = getContext().getSharedPreferences("productIdStorage", MODE_PRIVATE).edit();
        editor.putInt("productId", productId);
        editor.apply();
    }


    private int retrieveProductId() {
        int screenIdin;
        SharedPreferences prefs = getContext().getSharedPreferences("productIdStorage", MODE_PRIVATE);
        screenIdin = prefs.getInt("productId", 18); //0 is the default value.

        return screenIdin;
    }


}