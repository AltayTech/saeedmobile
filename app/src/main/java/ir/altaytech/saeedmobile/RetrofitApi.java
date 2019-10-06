package ir.altaytech.saeedmobile;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApi {
    Context mctx;

    public RetrofitApi(Context mctx) {
        this.mctx = mctx;
    }


    public Api createRetrofit(String baseURL) {

        int cacheSize = 10 * 1024 * 1024; // 10 MB
        Cache cache = new Cache(mctx.getCacheDir(), cacheSize);


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .build();
        OAuthInterceptor oauth1Woocommerce = new OAuthInterceptor.Builder()
                .consumerKey(mctx.getString(R.string.cunsomer_key))
                .consumerSecret(mctx.getString(R.string.consumersecret))
                .build();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .cache(cache)
                .addInterceptor(interceptor)
                .addInterceptor(oauth1Woocommerce)  // Interceptor oauth1Woocommerce added
                .build();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .client(client)
                .build();



        Api api = mRetrofit.create(Api.class);
        return api;
    }

}
