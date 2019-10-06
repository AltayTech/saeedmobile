package ir.altaytech.saeedmobile;


import java.util.List;

import ir.altaytech.saeedmobile.Model.AuthenticationToken;
import ir.altaytech.saeedmobile.Model.Customer;
import ir.altaytech.saeedmobile.Model.CustomersAddress;
import ir.altaytech.saeedmobile.Model.FilteredRequest;
import ir.altaytech.saeedmobile.Model.Orders;
import ir.altaytech.saeedmobile.Model.Product;
import ir.altaytech.saeedmobile.Model.ReqCustomerAllAddress;
import ir.altaytech.saeedmobile.Model.ReqCustomersAddress;
import ir.altaytech.saeedmobile.Model.ReqCustomersAddressRequest;
import ir.altaytech.saeedmobile.Model.ReqFilterResponse;
import ir.altaytech.saeedmobile.Model.ReqForgetPass;
import ir.altaytech.saeedmobile.Model.ReqMainPage;
import ir.altaytech.saeedmobile.Model.ReqOrder;
import ir.altaytech.saeedmobile.Model.ReqOrderList;
import ir.altaytech.saeedmobile.Model.ReqPayment;
import ir.altaytech.saeedmobile.Model.ReqProfileUpdate;
import ir.altaytech.saeedmobile.Model.ReqSearch;
import ir.altaytech.saeedmobile.Model.ReqShoppingCard;
import ir.altaytech.saeedmobile.Model.ReqString;
import ir.altaytech.saeedmobile.Model.ReqTransaction;
import ir.altaytech.saeedmobile.Model.ReqUserRegister;
import ir.altaytech.saeedmobile.Model.ReqWalletBalance;
import ir.altaytech.saeedmobile.Model.ShoppingCard;
import ir.altaytech.saeedmobile.Model.User;
import ir.altaytech.saeedmobile.Model.other.Coupon;
import ir.altaytech.saeedmobile.Model.other.Customers;
import ir.altaytech.saeedmobile.Model.other.Products;
import ir.altaytech.saeedmobile.Model.ReqCustomer;
import ir.altaytech.saeedmobile.Model.ReqLogin;
import ir.altaytech.saeedmobile.Model.ReqProduct;
import ir.altaytech.saeedmobile.Model.ReqSlider;
import ir.altaytech.saeedmobile.Model.Userpass;
import ir.altaytech.saeedmobile.Model.other.WalletTransaction;
import ir.altaytech.saeedmobile.Model.other.Billing;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface Api {


    @GET(URLs.EndPoint_Products)
    Call<List<Products>> getProductsCategorized(

            @Query("search") String search,
            @Query("attribute") String attribute,
            @Query("order") String order,
            @Query("category") String category,
            @Query("per_page") int per_page
    );

    @GET(URLs.EndPoint_Products)
    Call<List<Product>> getProductsTotal(

            @Query("search") String search,
            @Query("attribute") String attribute,
            @Query("order") String order,
            @Query("per_page") int per_page
    );


    @GET(URLs.EndPoint_Orders)
    Call<List<Orders>> RetrieveOrder(
            @Query("customer") int id
    );

    @PUT(URLs.EndPoint_Customers + "/{id}")
    Call<Customers> ChangePass(
            @Path("id") int groupId,
            @Query("password") String password
    );


    @POST(URLs.EndPoint_coupons)
    Call<Coupon> createCoupon(
            @Body Coupon coupon

    );


    @GET(URLs.EndPoint_Product)
    Call<ReqProduct> getProduct(
            @Query("ProductId") int ProductId
    );

    @POST(URLs.EndPoint_Login)
    Call<ReqLogin> login2(
            @Header("Content-Type") String contenttype,
            @Body Userpass userpass

    );

    @GET(URLs.EndPoint_Customers)
    Call<ReqCustomer> getCustomers(
            @Header("Content-Type") String contenttype,
            @Header("Authorization") String Authorization

    );

    @GET(URLs.EndPoint_MainPage)
    Call<ReqMainPage> getMainPage(
//            @Header("Authorization") String Authorization
    );

    @POST(URLs.EndPoint_ShoppingCart)
    Call<ReqProduct> addShoppingCart(
            @Header("Content-Type") String contenttype,
            @Query("ProductId") int ProductId

    );

    @GET(URLs.EndPoint_OrderList)
    Call<ReqOrderList> getOrderList(
            @Header("Authorization") String Authorization,
            @Query("ordersListStateFilter") int ordersListStateFilter


    );

    @GET(URLs.EndPoint_OrderDetail)
    Call<ReqOrder> getOrder(
            @Header("Authorization") String Authorization,
            @Query("HashOrderId") String HashOrderId

    );

    @POST(URLs.EndPoint_Registration)
    Call<ReqUserRegister> sendRegister(
            @Header("Content-Type") String contenttype,
            @Body User user

    );

    @POST(URLs.EndPoint_RegistrationConfirm)
    Call<ReqUserRegister> sendRegisterConfirm(
            @Header("Content-Type") String contenttype,
            @Body User user

    );

    @POST(URLs.EndPoint_IncreaseCartItem)
    Call<ReqShoppingCard> increaseCartItem(
            @Header("Content-Type") String contenttype,
            @Body ShoppingCard shoppingCard

    );

    @POST(URLs.EndPoint_DecreaseCartItem)
    Call<ReqShoppingCard> decreaseCartItem(
            @Header("Content-Type") String contenttype,
            @Body ShoppingCard shoppingCard

    );

    @DELETE(URLs.EndPoint_RemoveCartItem)
    Call<ReqShoppingCard> removeCartItem(
            @Header("Content-Type") String contenttype,
            @Query("HashCartId") String HashCartId,
            @Query("ProductId") int ProductId
    );

    @GET(URLs.EndPoint_Read)
    Call<ReqShoppingCard> readCartItems(
            @Header("Content-Type") String contenttype,
            @Query("HashCartId") String HashCartId
    );

    @GET(URLs.EndPoint_Search)
    Call<ReqSearch> searchReq(
            @Query("SearchValue") String SearchValue

    );

    @POST(URLs.EndPoint_Filters)
    Call<ReqFilterResponse> filterSearch(
            @Header("Content-Type") String contenttype,
            @Body FilteredRequest filteredRequest
//            @Query("SearchValue") String SearchValue

    );

    @POST(URLs.EndPoint_ForgetPassword_Confirm)
    Call<ReqForgetPass> fpConfirm(
            @Header("Content-Type") String contenttype,
            @Body User user

    );

    @POST(URLs.EndPoint_ForgetPassword_GenerateConfirmationCode)
    Call<ReqUserRegister> fpGenerateConfirmationCode(
            @Header("Content-Type") String contenttype,
            @Body User user

    );

    @POST(URLs.EndPoint_ForgetPassword_SetNewPassword)
    Call<ReqForgetPass> fpSetNewPassword(
            @Header("Content-Type") String contenttype,
            @Body User user

    );

    @GET(URLs.EndPoint_GetBalance)
    Call<ReqWalletBalance> getBalance(
            @Header("Content-Type") String contenttype,
            @Header("Authorization") String Authorization


    );

    @GET(URLs.EndPoint_GetBankPaymentUrl)
    Call<ReqPayment> getBankPaymentUrl(
            @Header("Content-Type") String contenttype,
            @Header("Authorization") String Authorization,
            @Query("TotalAmount") int TotalAmount


    );

    @GET(URLs.EndPoint_ReadTransaction)
    Call<ReqTransaction> getTransactions(
            @Header("Content-Type") String contenttype,
            @Header("Authorization") String Authorization

    );


    @POST(URLs.EndPoint_CustomersProfileUpdate)
    Call<ReqProfileUpdate> customersProfileUpdate(
            @Header("Content-Type") String contenttype,
            @Header("Authorization") String Authorization,
            @Body Customer customer


    );


    @POST(URLs.EndPoint_CustomersAddressCreate)
    Call<ReqString> customersAddressCreate(
            @Header("Content-Type") String contenttype,
            @Header("Authorization") String Authorization,
            @Body CustomersAddress customersAddress


    );

    @GET(URLs.EndPoint_CustomersAddressFind)
    Call<ReqCustomersAddress> customersAddressFind(
            @Header("Content-Type") String contenttype,
            @Header("Authorization") String Authorization,
            @Query("AddressId") int AddressId
    );

    @DELETE(URLs.EndPoint_CustomersAddressDelete)
    Call<ReqCustomersAddressRequest> customersAddressDelete(
            @Header("Content-Type") String contenttype,
            @Header("Authorization") String Authorization,
            @Query("AddressId") int AddressId
    );

    @PUT(URLs.EndPoint_CustomersAddressUpdate)
    Call<ReqCustomersAddressRequest> customersAddressUpdate(
            @Header("Content-Type") String contenttype,
            @Header("Authorization") String Authorization,
            @Body CustomersAddress customersAddress
    );

    @GET(URLs.EndPoint_CustomersAddressRead)
    Call<ReqCustomerAllAddress> customersAddressRead(
            @Header("Content-Type") String contenttype,
            @Header("Authorization") String Authorization
    );

    @GET(URLs.EndPoint_AreasReadByPoint)
    Call<ReqCustomersAddress> areasReadByPoint(
            @Header("Content-Type") String contenttype,
            @Header("Authorization") String Authorization,
            @Query("Lat") double Lat,
            @Query("Lng") double Lng

    );


    @GET(URLs.EndPoint_ChangePassword)
    Call<ReqForgetPass> changePassword(
            @Header("Content-Type") String contenttype,
            @Header("Authorization") String Authorization,
            @Query("OldPassword") String OldPassword,
            @Query("NewPassword") String NewPassword,
            @Query("ReNewPassword") String ReNewPassword


    );

}