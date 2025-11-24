package com.example.mall_management.model;

import java.util.Objects;

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

    // GETTERS & SETTERS
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

    // IMPORTANT — compatibil cu InFileRepository
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase)) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equals(id, purchase.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
