package ir.altaytech.saeedmobile.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import ir.altaytech.saeedmobile.SharedPrefManagerLogin;
import ir.altaytech.saeedmobile.fragments.FavoriteFragment;
import ir.altaytech.saeedmobile.fragments.HomeFragment;
import ir.altaytech.saeedmobile.fragments.ProductSearchFragment;
import ir.altaytech.saeedmobile.fragments.ProductViewFragment;
import ir.altaytech.saeedmobile.fragments.ShoppingCartFragment;
import ir.altaytech.saeedmobile.R;
import ir.altaytech.saeedmobile.SharedPrefManagerCustomer;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private Fragment fragment;
    private Fragment prefragment;
    private int starter;
    private static long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        toggle.setDrawerIndicatorEnabled(false);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
        toggle.setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);


        NavigationView navigationView = findViewById(R.id.nav_view);
        // get menu from navigationView
        Menu menu = navigationView.getMenu();

        // find MenuItem you want to change
        MenuItem nav_user = menu.findItem(R.id.nav_user);

        // set new title to the MenuItem
        if (SharedPrefManagerLogin.getInstance(getApplicationContext()).isLoggedIn()) {
            nav_user.setTitle("پروفایل کاربر");
        } else {
            nav_user.setTitle("ورود/ثبت نام");

        }
        navigationView.setNavigationItemSelectedListener(this);


        loadFragment(new HomeFragment());

        //getting bottom navigation view and attaching the listener
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        navigation.getMenu().getItem(0).setChecked(true);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/irsans.ttf");
        TextView myTextView = findViewById(R.id.mytextHead);
        myTextView.setTypeface(myTypeface);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }


        starter = 1;
        saveStatus(starter);
        fragment = new HomeFragment();
        prefragment = new HomeFragment();
    }

    @Override
    public void onBackPressed() {

        Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if (f instanceof ShoppingCartFragment) {
                // do operations
                if (!(f == prefragment)) {
                    loadFragment(prefragment);
                } else {
                    loadFragment(new HomeFragment());
                }
            } else if (f instanceof FavoriteFragment) {
                // do operations
                if (!(f == prefragment)) {
                    fragment = prefragment;

                    loadFragment(prefragment);
                } else {
                    loadFragment(new HomeFragment());

                }


            } else if (f instanceof ProductSearchFragment) {
                loadFragment(fragment);
                if (fragment.equals(new HomeFragment())) {
                    starter = 1;
                    saveStatus(starter);
                }

            } else {

                if (back_pressed + 2000 > System.currentTimeMillis()) super.onBackPressed();
                else
                    Toast.makeText(getBaseContext(), "برای خروج دوباره فشار دهید", Toast.LENGTH_SHORT).show();
                back_pressed = System.currentTimeMillis();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_shoppingCart) {
            if (!prefragment.equals(new ShoppingCartFragment()))
                prefragment = fragment;
            saveStatus(1);
            fragment = new ShoppingCartFragment();
            loadFragment(fragment);
            return true;
        } else if (id == R.id.action_search) {
            if (!prefragment.equals(new ProductSearchFragment()))
                prefragment = fragment;
            saveStatus(1);
            fragment = new ProductSearchFragment();
            loadFragment(fragment);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_user) {
            Intent loginIntent = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(loginIntent);

        } else if (id == R.id.nav_about) {
            Intent aboutIntent = new Intent(getApplicationContext(), AboutActivity.class);

            startActivity(aboutIntent);
        } else if (id == R.id.nav_contact) {
            Intent aboutIntent = new Intent(getApplicationContext(), ContactActivity.class);

            startActivity(aboutIntent);
        } else if (id == R.id.nav_favorite) {
            if (!prefragment.equals(new FavoriteFragment()))
                prefragment = fragment;
            saveStatus(1);

            fragment = new FavoriteFragment();
        } else if (id == R.id.nav_home) {
            if (!prefragment.equals(new HomeFragment()))
                prefragment = fragment;
            saveStatus(1);
            fragment = new HomeFragment();
        } else if (id == R.id.nav_shoppingcart) {
            if (!prefragment.equals(new ShoppingCartFragment()))
                prefragment = fragment;
            saveStatus(1);

            fragment = new ShoppingCartFragment();

        } else if (id == R.id.nav_orders) {
            Intent ordersIntent = new Intent(getApplicationContext(), OrderActivity.class);
            startActivity(ordersIntent);
        } else if (id == R.id.nav_exit) {
            SharedPrefManagerCustomer.getInstance(getApplicationContext()).logout();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        switch (item.getItemId()) {
            case R.id.navigation_home:
                if (!prefragment.equals(new HomeFragment()))
                    prefragment = fragment;
                saveStatus(1);
                fragment = new HomeFragment();
                break;

            case R.id.navigation_favorite:
                if (!prefragment.equals(new FavoriteFragment()))
                    prefragment = fragment;
                saveStatus(1);

                fragment = new FavoriteFragment();
                break;

            case R.id.navigation_profile:

                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                break;

        }

        return loadFragment(fragment);


    }

    private boolean loadFragment(Fragment fragment1) {
        //switching fragment
        if (fragment1 != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment1)
                    .commit();

            return true;
        }
        return false;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void saveStatus(int starter) {
        SharedPreferences.Editor editor = getSharedPreferences("StatusStorage", MODE_PRIVATE).edit();
        editor.putInt("starter", starter);
        editor.apply();
    }


}
