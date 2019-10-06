package ir.altaytech.saeedmobile;

import android.content.Context;
import android.content.SharedPreferences;

import ir.altaytech.saeedmobile.Model.AuthenticationToken2;

/**
 * Created by Altay on 2/7/2018.
 */

public class SharedPrefManagerLogin {

    //the constants
    private static final String SHARED_PREF_NAME = "customerinformation";
    //    private static final String SHARED_PREF_NAMEPhone = "PhoneNumber";
    private static SharedPrefManagerLogin mInstance;
    private static Context mCtx;

    private String userFullName = "keyuserFullName";
    private static final String UserName = "keyUserName";
    private String Token = "keyToken";


    private SharedPrefManagerLogin(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManagerLogin getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManagerLogin(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(AuthenticationToken2 customer) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(userFullName, customer.getUserFullName());
        editor.putString(UserName, customer.getUserName());
        editor.putString(Token, customer.getToken());

        editor.apply();

    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Token, null) != null;
    }

    //this method will give the logged in user
    public AuthenticationToken2 getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        Log.d("retrofit shPRPhone2", sharedPreferences.getString(phonenumber, null));

        return new AuthenticationToken2(
                sharedPreferences.getString(Token, null),
                sharedPreferences.getString(userFullName, null),
                sharedPreferences.getString(UserName, null)

        );

    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
//        mCtx.startActivity(new Intent(mCtx, LoginActivity.class));
    }
}