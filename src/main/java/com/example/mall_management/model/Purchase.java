// src/main/java/com/example/mall_management/model/Purchase.java
package com.example.mall_management.model;

public class Purchase {
    private String id;
    private String customerId;
    private String shopId;
    private String currency;
    private double amount;

    public Purchase(String id, String customerId, String shopId, String currency, double amount) {
        this.id = id;
        this.customerId = customerId;
        this.shopId = shopId;
        this.currency = currency;
        this.amount = amount;
    }

    // Getters
    public String getId() { return id; }
    public String getCustomerId() { return customerId; }
    public String getShopId() { return shopId; }
    public String getCurrency() { return currency; }
    public double getAmount() { return amount; }
}