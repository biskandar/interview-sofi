package com.sofi.interview;

public final class TransactionBuilder {

    private int userId;
    private String merchantName;
    private int merchantId;
    private double price;
    private String purchaseDate;
    private int id;

    private TransactionBuilder() {
    }

    public static TransactionBuilder aTransaction() {
        return new TransactionBuilder();
    }

    public TransactionBuilder withUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public TransactionBuilder withMerchantName(String merchantName) {
        this.merchantName = merchantName;
        return this;
    }

    public TransactionBuilder withMerchantId(int merchantId) {
        this.merchantId = merchantId;
        return this;
    }

    public TransactionBuilder withPrice(double price) {
        this.price = price;
        return this;
    }

    public TransactionBuilder withPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
        return this;
    }

    public TransactionBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public Transaction build() {
        Transaction transaction = new Transaction();
        transaction.setUserId(userId);
        transaction.setMerchantName(merchantName);
        transaction.setMerchantId(merchantId);
        transaction.setPrice(price);
        transaction.setPurchaseDate(purchaseDate);
        transaction.setId(id);
        return transaction;
    }
}
