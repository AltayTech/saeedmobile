package ir.altaytech.saeedmobile.Model;


import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SCIT on 3/10/2017.
 */
@Keep
public class Transaction {

    @SerializedName("RegDateTime")
    private String RegDateTime;
    @SerializedName("PreviousBalance")
    private int PreviousBalance;
    @SerializedName("Deposit")
    private int Deposit;
    @SerializedName("Withdraw")
    private int Withdraw;
    @SerializedName("Balance")
    private int Balance;
    @SerializedName("OrderId")
    private String OrderId;
    @SerializedName("FinancialTransactionTypes")
    private String FinancialTransactionTypes;


    public Transaction(String regDateTime, int previousBalance, int deposit, int withdraw, int balance, String orderId, String financialTransactionTypes) {
        RegDateTime = regDateTime;
        PreviousBalance = previousBalance;
        Deposit = deposit;
        Withdraw = withdraw;
        Balance = balance;
        OrderId = orderId;
        FinancialTransactionTypes = financialTransactionTypes;
    }

    public String getRegDateTime() {
        return RegDateTime;
    }

    public int getPreviousBalance() {
        return PreviousBalance;
    }

    public int getDeposit() {
        return Deposit;
    }

    public int getWithdraw() {
        return Withdraw;
    }

    public int getBalance() {
        return Balance;
    }

    public String getOrderId() {
        return OrderId;
    }

    public String getFinancialTransactionTypes() {
        return FinancialTransactionTypes;
    }
}
