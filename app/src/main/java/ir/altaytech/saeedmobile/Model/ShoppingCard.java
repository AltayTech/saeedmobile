package ir.altaytech.saeedmobile.Model;


import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SCIT on 3/10/2017.
 */
@Keep
public class ShoppingCard {

    @SerializedName("HashCartId")
    private String HashCartId;
    @SerializedName("IsCartEmpty")
    private boolean IsCartEmpty;
    @SerializedName("cartStatusTypes")
    private int cartStatusTypes;
    @SerializedName("CartInfo")
    private CartInfo CartInfo;
    @SerializedName("SubTotalAmount")
    private int SubTotalAmount;
    @SerializedName("FinalSubTotalAmount")
    private int FinalSubTotalAmount;
    @SerializedName("IsCouponDiscountApplied")
    private Boolean IsCouponDiscountApplied;
    @SerializedName("AmountPayable")
    private int AmountPayable;
    @SerializedName("DeliveryFee")
    private int DeliveryFee;
    @SerializedName("OrderDetailsCount")
    private int OrderDetailsCount;
    @SerializedName("IsPickup")
    private Boolean IsPickup;
    @SerializedName("CustomerAddress")
    private String CustomerAddress;
    @SerializedName("OrderDetails")
    private List<OrderDetail> OrderDetails;

    @SerializedName("TaxRate")
    private int TaxRate;
    @SerializedName("Tax")
    private int Tax;

    @SerializedName("Comment")
    private String Comment;
    @SerializedName("CarrierId")
    private String CarrierId;

    @SerializedName("ProductId")
    private int ProductId;

    public ShoppingCard(String hashCartId, int productId) {
        HashCartId = hashCartId;
        ProductId = productId;
    }

    public ShoppingCard(String hashCartId, boolean isCartEmpty, int cartStatusTypes,
                        CartInfo cartInfo, int subTotalAmount, int finalSubTotalAmount,
                        Boolean isCouponDiscountApplied, int amountPayable, int deliveryFee,
                        int orderDetailsCount, Boolean isPickup, String customerAddress,
                        List<OrderDetail> orderDetails, int taxRate, int tax, String comment,
                        String carrierId) {

        HashCartId = hashCartId;
        IsCartEmpty = isCartEmpty;
        this.cartStatusTypes = cartStatusTypes;
        CartInfo = cartInfo;
        SubTotalAmount = subTotalAmount;
        FinalSubTotalAmount = finalSubTotalAmount;
        IsCouponDiscountApplied = isCouponDiscountApplied;
        AmountPayable = amountPayable;
        DeliveryFee = deliveryFee;
        OrderDetailsCount = orderDetailsCount;
        IsPickup = isPickup;
        CustomerAddress = customerAddress;
        OrderDetails = orderDetails;
        TaxRate = taxRate;
        Tax = tax;
        Comment = comment;
        CarrierId = carrierId;
    }

    public int getProductId() {
        return ProductId;
    }

    public String getHashCartId() {
        return HashCartId;
    }

    public boolean isCartEmpty() {
        return IsCartEmpty;
    }

    public int getCartStatusTypes() {
        return cartStatusTypes;
    }

    public ir.altaytech.saeedmobile.Model.CartInfo getCartInfo() {
        return CartInfo;
    }

    public int getSubTotalAmount() {
        return SubTotalAmount;
    }

    public int getFinalSubTotalAmount() {
        return FinalSubTotalAmount;
    }

    public Boolean getCouponDiscountApplied() {
        return IsCouponDiscountApplied;
    }

    public int getAmountPayable() {
        return AmountPayable;
    }

    public int getDeliveryFee() {
        return DeliveryFee;
    }

    public int getOrderDetailsCount() {
        return OrderDetailsCount;
    }

    public Boolean getPickup() {
        return IsPickup;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public List<OrderDetail> getOrderDetails() {
        return OrderDetails;
    }

    public int getTaxRate() {
        return TaxRate;
    }

    public int getTax() {
        return Tax;
    }

    public String getComment() {
        return Comment;
    }

    public String getCarrierId() {
        return CarrierId;
    }
}
