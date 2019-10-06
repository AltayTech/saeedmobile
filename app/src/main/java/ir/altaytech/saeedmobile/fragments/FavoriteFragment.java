package ir.altaytech.saeedmobile.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ir.altaytech.saeedmobile.Api;
import ir.altaytech.saeedmobile.DatabaseManager;
import ir.altaytech.saeedmobile.Model.Product;
import ir.altaytech.saeedmobile.Model.other.Product2;
import ir.altaytech.saeedmobile.Model.other.Products;
import ir.altaytech.saeedmobile.R;
import ir.altaytech.saeedmobile.RetrofitApi;
import ir.altaytech.saeedmobile.URLs;
import ir.altaytech.saeedmobile.adapters.ProductAdapter_favorite;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Altay on 2/7/2018.
 */

public class FavoriteFragment extends Fragment {


    List<Product> productList;
    RecyclerView recylcerView_favorite;
    View rootView;
    List<Product> favoriteProductList;
    private ProductViewFragment productViewFragment;
    private int itemId;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_favorite, container, Boolean.parseBoolean(null));

        recylcerView_favorite = rootView.findViewById(R.id.recylcerView_favorite);
        recylcerView_favorite.setHasFixedSize(true);
        recylcerView_favorite.setLayoutManager(new GridLayoutManager(getContext(), 3));

        //initializing the productlist
        productList = new ArrayList<>();
        favoriteProductList = new ArrayList<>();

        productViewFragment = new ProductViewFragment();

        DatabaseManager.openDatabase(getContext());

        favoriteProductList = DatabaseManager.readListFavorite();

        try {
            productAdapter(favoriteProductList);
        } catch (Exception e) {

            e.printStackTrace();
        }

        return rootView;

    }


    private void productAdapter(List<Product> productList) {
        final ProductAdapter_favorite.ProductsAdapter adapter = new ProductAdapter_favorite.ProductsAdapter(getActivity(), productList, new ProductAdapter_favorite.ClickListener() {

            @Override
            public void onLongClicked(int position) {

            }

            @Override
            public void onShoppingCartClicked(int position) {


            }

            @Override
            public void onItemlicked(int position) {
                itemId = favoriteProductList.get(position).getId();

                clickedItem(itemId);

            }

            @Override
            public void onRemoveItemClicked(int position) {
                favoriteProductList = DatabaseManager.readListFavorite();
            }

        });
        try {
            recylcerView_favorite.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void clickedItem(int itemId) {
        saveProductId(itemId);
        changeFragment(productViewFragment);
    }


    private void changeFragment(Fragment targetFragment) {

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

    }

    private void saveProductId(int productId) {
        SharedPreferences.Editor editor = getContext().getSharedPreferences("productIdStorage", MODE_PRIVATE).edit();
        editor.putInt("productId", productId);
        editor.apply();
    }

}