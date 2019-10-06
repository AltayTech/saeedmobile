package ir.altaytech.saeedmobile;

/**
 * Created by Altay on 2/7/2018.
 */

public class URLs {

    public static final String EndPoint_Orders = "/wp-json/wc/v2/orders";

    public static final String EndPoint_coupons = "/wp-json/wc/v2/coupons";

    public static final String ROOT_URL = "http://demo1.karasystak.ir";
    public static final String EndPoint_Products = "/api/v1/ContentManagement/AmazingOffers/Read";
    public static final String EndPoint_Login = "/api/v1/Identity/Account/Login";
    public static final String EndPoint_Customers = "/api/v1/Identity/CustomersProfile/ReadProfile";
    public static final String EndPoint_Slider = "/api/v1/ContentManagement/Sliders/Read";
    public static final String EndPoint_MainPage = "/api/v1/ContentManagement/Contents/Read";
    public static final String EndPoint_Product = "/api/v1/ProductManagement/ProductInformation/Read";
    public static final String EndPoint_ShoppingCart = "/api/v1/Orders/Carts/IncreaseCartItem";

    public static final String EndPoint_OrderList = "/api/v1/Orders/OrdersList/Read";
    public static final String EndPoint_OrderDetail = "/api/v1/Orders/Orders/Read";

    public static final String EndPoint_Registration = "/api/v1/Identity/UserRegister/Register/";
    public static final String EndPoint_RegistrationConfirm = "/api/v1/Identity/UserRegister/Confirm/";

    public static final String EndPoint_IncreaseCartItem = "/api/v1/Orders/Carts/IncreaseCartItem/";
    public static final String EndPoint_DecreaseCartItem = "/api/v1/Orders/Carts/DecreaseCartItem/";
    public static final String EndPoint_Read = "/api/v1/Orders/Carts/Read/";
    public static final String EndPoint_RemoveCartItem = "/api/v1/Orders/Carts/RemoveCartItem/";
    public static final String EndPoint_AppendComment = "/api/v1/Orders/Carts/AppendComment/";

    public static final String EndPoint_Search = "/api/v1/ProductManagement/Search/Read/";
    public static final String EndPoint_Filters = "/api/v1/ProductManagement/Filters/Read/";



    public static final String EndPoint_ForgetPassword_GenerateConfirmationCode = "/api/v1/Identity/ForgetPassword/GenerateConfirmationCode/";
    public static final String EndPoint_ForgetPassword_Confirm = "/api/v1/Identity/ForgetPassword/Confirm/";
    public static final String EndPoint_ForgetPassword_SetNewPassword = "/api/v1/Identity/ForgetPassword/SetNewPassword/";

    public static final String EndPoint_GetBankPaymentUrl = "/api/v1/Orders/CreditPayment/GetBankPaymentUrl/";
    public static final String EndPoint_GetBalance = "/api/v1/Identity/CustomersFinince/GetBalance/";
    public static final String EndPoint_ChangePassword = "/api/v1/Identity/Customer/ChangePassword/";
    public static final String EndPoint_ReadTransaction = "/api/v1/Identity/CustomersFinince/ReadTransaction/";
    public static final String EndPoint_CustomersProfileUpdate = "/api/v1/Identity/CustomersProfile/Update/";
    public static final String EndPoint_ReadProfile = "/api/v1/Identity/CustomersProfile/ReadProfile/";

    public static final String EndPoint_CustomersAddressCreate = "/api/v1/Identity/CustomersAddress/Create/";
    public static final String EndPoint_CustomersAddressFind = "/api/v1/Identity/CustomersAddress/Find/";
    public static final String EndPoint_CustomersAddressDelete = "/api/v1/Identity/CustomersAddress/Delete/";
    public static final String EndPoint_CustomersAddressUpdate= "/api/v1/Identity/CustomersAddress/Update/";
    public static final String EndPoint_CustomersAddressRead = "/api/v1/Identity/CustomersAddress/Read/";
    public static final String EndPoint_AreasReadByPoint = "/api/v1/BasicDefinitions/Areas/ReadByPoint/";

    public static final String EndPoint_OrderPaymentGetBankPaymentUrl = "/api/v1/Orders/OrderPayment/GetBankPaymentUrl/";
    public static final String EndPoint_CartInvoiceRead = "/v1/Orders/CartInvoice/Read/";


}