package ir.altaytech.saeedmobile.Model;


import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SCIT on 3/10/2017.
 */
@Keep
public class Order {

    @SerializedName("HashOrderId")
    private String HashOrderId;
    @SerializedName("OrderId")
    private int OrderId;
    @SerializedName("CouponDiscount")
    private int CouponDiscount;
    @SerializedName("DiscountCost")
    private int DiscountCost;
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
    @SerializedName("TransfereeMobNo")
    private String TransfereeMobNo;
    @SerializedName("PostalCode")
    private String PostalCode;
    @SerializedName("orderStatusTypes")
    private int orderStatusTypes;

    public Order(String hashOrderId, int orderId, int couponDiscount, int discountCost, int subTotalAmount, int finalSubTotalAmount,
                 Boolean isCouponDiscountApplied, int amountPayable, int deliveryFee, int orderDetailsCount, Boolean isPickup,
                 String customerAddress, List<OrderDetail> orderDetails, int taxRate, int tax, String comment, String transfereeMobNo,
                 String postalCode, int orderStatusTypes) {
        HashOrderId = hashOrderId;
        OrderId = orderId;
        CouponDiscount = couponDiscount;
        DiscountCost = discountCost;
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
        TransfereeMobNo = transfereeMobNo;
        PostalCode = postalCode;
        this.orderStatusTypes = orderStatusTypes;
    }


    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public List<OrderDetail> getOrderDetails() {
        return OrderDetails;
    }

    public String getHashOrderId() {
        return HashOrderId;
    }

    public int getOrderId() {
        return OrderId;
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

    public int getTaxRate() {
        return TaxRate;
    }

    public int getTax() {
        return Tax;
    }

    public String getComment() {
        return Comment;
    }

    public String getTransfereeMobNo() {
        return TransfereeMobNo;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public int getOrderStatusTypes() {
        return orderStatusTypes;
    }
}
