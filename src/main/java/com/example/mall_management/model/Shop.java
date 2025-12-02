package com.example.mall_management.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "shops")
public class Shop {

    @Id
    private String id;   // generat în service cu UUID

    @Column(nullable = false)
    private String name;

    @Column(name = "owner_name", nullable = false)
    private String ownerName;

    @Column(name = "area_sqm", nullable = false)
    private double areaSqm;

    // deocamdată NU o mapăm în JPA, doar în memorie
    @Transient
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
        purchases.add(p);    // fara p.setShop(this);
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
