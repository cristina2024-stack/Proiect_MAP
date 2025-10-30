// src/main/java/com/example/mall_management/model/Customer.java
package com.example.mall_management.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String id;
    private String name;
    private String email;
    private String currency;
    private List<Purchase> purchases = new ArrayList<>();
    public Customer() { }
    public Customer(String id, String name, String email, String currency) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.currency = currency;
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getCurrency() { return currency; }
    public List<Purchase> getPurchases() { return purchases; }
    public void setId(String id) { this.id = id; }            // <-- adăugat
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setCurrency(String currency) { this.currency = currency; }
    public void addPurchase(Purchase purchase) {
        purchases.add(purchase);
    }
}
