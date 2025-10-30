package com.example.mall_management.model;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private String id;
    private String name;
    private String ownerName;
    private double areaSqm;
    private List<Purchase> purchases = new ArrayList<>();

    public Shop(String id, String name, String ownerName, double areaSqm) {
        this.id = id;
        this.name = name;
        this.ownerName = ownerName;
        this.areaSqm = areaSqm;
    }

    public void addPurchase(Purchase p) { purchases.add(p); }

    // getters/setters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getOwnerName() { return ownerName; }
    public double getAreaSqm() { return areaSqm; }
    public List<Purchase> getPurchases() { return purchases; }
    public void setName(String name) { this.name = name; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
    public void setAreaSqm(double areaSqm) { this.areaSqm = areaSqm; }
}

