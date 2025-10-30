// src/main/java/com/example/mall_management/repository/PurchaseRepository.java
package com.example.mall_management.repository;

import com.example.mall_management.model.Purchase;
import java.util.ArrayList;
import java.util.List;

public class Purchase_Repository extends InMemoryRepository<Purchase> {

    public Purchase_Repository() {
        super(Purchase.class);
    }

    public List<Purchase> findByCustomerId(String customerId) {
        List<Purchase> result = new ArrayList<Purchase>();
        List<Purchase> allPurchases = findAll();

        for (int i = 0; i < allPurchases.size(); i++) {
            Purchase p = allPurchases.get(i);
            if (p.getCustomerId() != null && p.getCustomerId().equals(customerId)) {
                result.add(p);
            }
        }

        return result;
    }

    public List<Purchase> findByShopId(String shopId) {
        List<Purchase> result = new ArrayList<Purchase>();
        List<Purchase> allPurchases = findAll();

        for (int i = 0; i < allPurchases.size(); i++) {
            Purchase p = allPurchases.get(i);
            if (p.getShopId() != null && p.getShopId().equals(shopId)) {
                result.add(p);
            }
        }

        return result;
    }

    public List<Purchase> findByCurrency(String currency) {
        List<Purchase> result = new ArrayList<Purchase>();
        List<Purchase> allPurchases = findAll();

        for (int i = 0; i < allPurchases.size(); i++) {
            Purchase p = allPurchases.get(i);
            if (p.getCurrency() != null && p.getCurrency().equalsIgnoreCase(currency)) {
                result.add(p);
            }
        }

        return result;
    }

    public List<Purchase> findAboveAmount(double minAmount) {
        List<Purchase> result = new ArrayList<Purchase>();
        List<Purchase> allPurchases = findAll();

        for (int i = 0; i < allPurchases.size(); i++) {
            Purchase p = allPurchases.get(i);
            if (p.getAmount() > minAmount) {
                result.add(p);
            }
        }

        return result;
    }
}
