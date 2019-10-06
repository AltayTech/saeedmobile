package ir.altaytech.saeedmobile;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import ir.altaytech.saeedmobile.Model.Orders;
import ir.altaytech.saeedmobile.activities.LoginActivity;
import ir.altaytech.saeedmobile.Model.other.Coupon;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CouponService extends Service {
    private List<Orders> ordersList;
    private int customerId;
    private String representerPhone;

    public CouponService() {
    }

    @Override
    public void onCreate() {

        ordersList = new ArrayList<>();
        if (SharedPrefManagerLogin.getInstance(getApplicationContext()).isLoggedIn()) {

            okhttpReqPost(URLs.ROOT_URL, customerId);
        } else {
            Toast.makeText(getApplicationContext(), "لطفا وارد شوید", Toast.LENGTH_SHORT).show();
            Intent loginIntent = new Intent(getApplication(), LoginActivity.class);
            startActivity(loginIntent);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        if (SharedPrefManagerLogin.getInstance(getApplicationContext()).isLoggedIn()) {

            okhttpReqPost(URLs.ROOT_URL, customerId);
        } else {
            Toast.makeText(getApplicationContext(), "لطفا وارد شوید", Toast.LENGTH_SHORT).show();
            Intent loginIntent = new Intent(getApplication(), LoginActivity.class);
            startActivity(loginIntent);
        }

//        throw new UnsupportedOperationException("Not yet implemented");

        return null;
    }

    private void okhttpReqPost(String baseURL, int customerId) {

        OAuthInterceptor oauth1Woocommerce = new OAuthInterceptor.Builder()
                .consumerKey(getString(R.string.cunsomer_key))
                .consumerSecret(getString(R.string.consumersecret))
                .build();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(oauth1Woocommerce)// Interceptor oauth1Woocommerce added
                .build();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        Api api = mRetrofit.create(Api.class);

        Call<List<Orders>> call = api.RetrieveOrder(customerId);

        call.enqueue(new Callback<List<Orders>>() {
            @Override
            public void onResponse(Call<List<Orders>> call, retrofit2.Response<List<Orders>> response) {
//                progressDialog.dismiss();

                if (response.isSuccessful()) {

                    if (response.body().size() > 1) {

//                        Toast.makeText(getApplicationContext(), "ارتباط برقرار نشد", Toast.LENGTH_SHORT).show();

                    } else {

                        String code = String.valueOf(randGen());
                        okhttpCreateCoupon(URLs.ROOT_URL, code, "10", false, 1);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "ارتباط برقرار نشد", Toast.LENGTH_SHORT).show();
                    Log.d("retrofit error", "ارتباط برقرار نشد");

                }
            }

            @Override
            public void onFailure(Call<List<Orders>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "ارتباط برقرار نشد", Toast.LENGTH_SHORT).show();
//                Log.d("retrofit error", "ارتباط برقرار نشد");
            }
        });
    }

    private void okhttpCreateCoupon(String baseURL, final String code, String amount, Boolean individual_use, int usage_limit) {

        OAuthInterceptor oauth1Woocommerce = new OAuthInterceptor.Builder()
                .consumerKey(getString(R.string.cunsomer_key))
                .consumerSecret(getString(R.string.consumersecret))
                .build();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(oauth1Woocommerce)// Interceptor oauth1Woocommerce added
                .build();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        Api api = mRetrofit.create(Api.class);

//        List<MetaData> metaData;
//        metaData = new ArrayList<>();
//
//        metaData.add(new MetaData("representer", representer));

        Coupon coupon = new Coupon(
                code,
                amount,
//                date_expires,
                individual_use,
                usage_limit,
                "percent"
        );


        Call<Coupon> call = api.createCoupon(coupon);

        call.enqueue(new Callback<Coupon>() {
            @Override
            public void onResponse(Call<Coupon> call, Response<Coupon> response) {

//                 Hiding the progress dialog after all task complete.
//                progressDialog.dismiss();
                Log.e("ServerResponse", response.message());

                if (response.isSuccessful()) {

                    postVerificationCode(urlBuilder("Dabba" +
                            "gh1910", "14142020149", representerPhone, "کد تخفیف معرفی برای شما:" + code));
                    Toast.makeText(getApplicationContext(), "کد تخفیف به معرف شما ارسال شد", Toast.LENGTH_SHORT).show();

//                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));


                } else {
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                    Log.d("authorized", response.message());
                }

            }

            @Override
            public void onFailure(Call<Coupon> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("retrofit error", t.getMessage());
            }
        });
    }


    public int randGen() {
        Random r = new Random();

        return r.nextInt(1000000 - 100000) + 100000;
    }

    public String urlBuilder(String username, String password, String phone, String message) {


        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("raygansms.com")
                .appendPath("SendMessageWithCode.ashx")
                .appendQueryParameter("Username", username)
                .appendQueryParameter("Password", password)
                .appendQueryParameter("Mobile", String.valueOf(phone))
                .appendQueryParameter("Message", message)
        ;
        String myUrl = builder.build().toString();
        Log.e("myUrl", String.valueOf(myUrl));
        return myUrl;
    }

    private void postVerificationCode(String baseURL) {


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, baseURL,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();

                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                mTextView.setText("That didn't work!");
            }
        });

        queue.add(stringRequest);
    }
}
