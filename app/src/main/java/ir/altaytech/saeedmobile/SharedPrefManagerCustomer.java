package ir.altaytech.saeedmobile;

import android.content.Context;
import android.content.SharedPreferences;

import ir.altaytech.saeedmobile.Model.AuthenticationToken2;
import ir.altaytech.saeedmobile.Model.Customer;
import ir.altaytech.saeedmobile.Model.other.Customers;

/**
 * Created by Altay on 2/7/2018.
 */

public class SharedPrefManagerCustomer {

    //the constants
    private static final String SHARED_PREF_NAME = "customerinformation";
    //    private static final String SHARED_PREF_NAMEPhone = "PhoneNumber";
    private static SharedPrefManagerCustomer mInstance;
    private static Context mCtx;

    private String FirstName = "keyFirstName";
    private String UserName = "keyUserName";
    private String LastName = "keyLastName";
    private String IsActive = "keyIsActive";
    private String NationalCode = "keyNationalCode";
    private String IsEmailNewsLatterSubsciber = "keyIsEmailNewsLatterSubsciber";
    private String IsSMSNewsLatterSubsciber = "keyIsSMSNewsLatterSubsciber";
    private String BankCardNo = "keyBankCardNo";
    private String Email = "keyEmail";


    private SharedPrefManagerCustomer(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManagerCustomer getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManagerCustomer(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(Customer customer) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(FirstName, customer.getFirstName());
        editor.putString(UserName, customer.getUserName());
        editor.putString(LastName, customer.getLastName());
        editor.putString(IsActive, customer.getIsActive());
        editor.putString(NationalCode, customer.getNationalCode());
        editor.putBoolean(IsEmailNewsLatterSubsciber, customer.getEmailNewsLatterSubsciber());
        editor.putBoolean(IsSMSNewsLatterSubsciber, customer.getSMSNewsLatterSubsciber());
        editor.putString(BankCardNo, customer.getBankCardNo());
        editor.putString(Email, customer.getEmail());

        editor.apply();

    }


    //this method will give the logged in user
    public Customer getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        Log.d("retrofit shPRPhone2", sharedPreferences.getString(phonenumber, null));

        return new Customer(
                sharedPreferences.getString(FirstName, null),
                sharedPreferences.getString(UserName, null),
                sharedPreferences.getString(LastName, null),
                sharedPreferences.getString(IsActive, null),
                sharedPreferences.getString(NationalCode, null),
                sharedPreferences.getBoolean(IsEmailNewsLatterSubsciber,false),
                sharedPreferences.getBoolean(IsSMSNewsLatterSubsciber, false),
                sharedPreferences.getString(BankCardNo, null),
                sharedPreferences.getString(Email, null)

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