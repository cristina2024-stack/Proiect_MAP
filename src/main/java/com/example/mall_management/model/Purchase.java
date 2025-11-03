package com.example.mall_management.model;

public class Purchase {
    private String id;
    private String customerId;
    private String shopId;
    private String currency;
    private double amount;

    public Purchase() {
    }


    public Purchase(String id, String customerId, String shopId, String currency, double amount) {
        this.id = id;
        this.customerId = customerId;
        this.shopId = shopId;
        this.currency = currency;
        this.amount = amount;
    }

    //
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
