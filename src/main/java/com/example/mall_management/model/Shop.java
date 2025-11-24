package com.example.mall_management.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Shop {

    private String id;
    private String name;
    private String ownerName;
    private double areaSqm;
    private List<Purchase> purchases = new ArrayList<>();

    public Shop() {}

    public Shop(String id, String name, String ownerName, double areaSqm) {
        this.id = id;
        this.name = name;
        this.ownerName = ownerName;
        this.areaSqm = areaSqm;
    }

    // PURCHASES
    public void addPurchase(Purchase p) {
        purchases.add(p);
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    // GETTERS & SETTERS
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getAreaSqm() {
        return areaSqm;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setAreaSqm(double areaSqm) {
        this.areaSqm = areaSqm;
    }

    // EQUALS & HASHCODE (IMPORTANT!)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shop)) return false;
        Shop shop = (Shop) o;
        return Objects.equals(id, shop.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
