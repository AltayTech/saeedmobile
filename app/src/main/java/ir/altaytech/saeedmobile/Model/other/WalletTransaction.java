package ir.altaytech.saeedmobile.Model.other;

import java.io.Serializable;

public class WalletTransaction implements Serializable {


    private int transaction_id;
    private int blog_id;
    private int user_id;
    private String type;
    private String amount;
    private String balance;
    private String currency;
    private String details;
    private String deleted;
    private String date;

    public WalletTransaction(int transaction_id, int user_id, String type, String amount, String balance) {
        this.transaction_id = transaction_id;
        this.user_id = user_id;
        this.type = type;
        this.amount = amount;
        this.balance = balance;
    }

    public WalletTransaction(int user_id, String type, String amount) {
        this.user_id = user_id;
        this.type = type;
        this.amount = amount;
    }

    public WalletTransaction(int transaction_id, int blog_id, int user_id, String type, String amount, String balance, String currency, String details, String deleted, String date) {
        this.transaction_id = transaction_id;
        this.blog_id = blog_id;
        this.user_id = user_id;
        this.type = type;
        this.amount = amount;
        this.balance = balance;
        this.currency = currency;
        this.details = details;
        this.deleted = deleted;
        this.date = date;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public int getBlog_id() {
        return blog_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getType() {
        return type;
    }

    public String getAmount() {
        return amount;
    }

    public String getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }

    public String getDetails() {
        return details;
    }

    public String getDeleted() {
        return deleted;
    }

    public String getDate() {
        return date;
    }
}
