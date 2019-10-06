package ir.altaytech.saeedmobile.Model;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Altay on 2/7/2018.
 */
@Keep
public class CartInfo {
    @SerializedName("IsAddressApplied")
    private boolean IsAddressApplied;
    @SerializedName("CouponDiscount")
    private int CouponDiscount;
    @SerializedName("DiscountCost")
    private int DiscountCost;
    @SerializedName("SubTotalAmount")
    private int SubTotalAmount;
    @SerializedName("FinalSubTotalAmount")
    private int FinalSubTotalAmount;
    @SerializedName("IsCouponDiscountApplied")
    private boolean IsCouponDiscountApplied;
    @SerializedName("AmountPayable")
    private int AmountPayable;
    @SerializedName("DeliveryFee")
    private int DeliveryFee;
    @SerializedName("CartDetailsCount")
    private int CartDetailsCount;
    @SerializedName("CustomerAddressId")
    private String CustomerAddressId;
    @SerializedName("IsPickup")
    private Boolean IsPickup;
    @SerializedName("CustomerAddress")
    private String CustomerAddress;
    @SerializedName("CartDetails")
    private List<ShoppingCardProduct> CartDetails;
    @SerializedName("TaxRate")
    private int TaxRate;
    @SerializedName("Tax")
    private int Tax;
    @SerializedName("Comment")
    private String Comment;
    @SerializedName("CarrierId")
    private String CarrierId;


    public CartInfo(boolean isAddressApplied, int couponDiscount, int discountCost,
                    int subTotalAmount, int finalSubTotalAmount, boolean isCouponDiscountApplied,
                    int amountPayable, int deliveryFee, int cartDetailsCount,
                    String customerAddressId, Boolean isPickup, String customerAddress,
                    List<ShoppingCardProduct> cartDetails, int taxRate, int tax, String comment,
                    String carrierId) {
        IsAddressApplied = isAddressApplied;
        CouponDiscount = couponDiscount;
        DiscountCost = discountCost;
        SubTotalAmount = subTotalAmount;
        FinalSubTotalAmount = finalSubTotalAmount;
        IsCouponDiscountApplied = isCouponDiscountApplied;
        AmountPayable = amountPayable;
        DeliveryFee = deliveryFee;
        CartDetailsCount = cartDetailsCount;
        CustomerAddressId = customerAddressId;
        IsPickup = isPickup;
        CustomerAddress = customerAddress;
        CartDetails = cartDetails;
        TaxRate = taxRate;
        Tax = tax;
        Comment = comment;
        CarrierId = carrierId;
    }

    public boolean isAddressApplied() {
        return IsAddressApplied;
    }

    public int getCouponDiscount() {
        return CouponDiscount;
    }

    public int getDiscountCost() {
        return DiscountCost;
    }

    public int getSubTotalAmount() {
        return SubTotalAmount;
    }

    public int getFinalSubTotalAmount() {
        return FinalSubTotalAmount;
    }

    public boolean isCouponDiscountApplied() {
        return IsCouponDiscountApplied;
    }

    public int getAmountPayable() {
        return AmountPayable;
    }

    public int getDeliveryFee() {
        return DeliveryFee;
    }

    public int getCartDetailsCount() {
        return CartDetailsCount;
    }

    public String getCustomerAddressId() {
        return CustomerAddressId;
    }

    public Boolean getPickup() {
        return IsPickup;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public List<ShoppingCardProduct> getCartDetails() {
        return CartDetails;
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