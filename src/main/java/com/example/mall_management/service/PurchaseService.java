package com.example.mall_management.service;

import com.example.mall_management.model.Purchase;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    private List<Purchase> purchases = new ArrayList<>();

    public List<Purchase> getAllPurchases() {
        return purchases;
    }

    public Optional<Purchase> getPurchaseById(String id) {
        for (Purchase p : purchases) {
            if (p.getId().equals(id)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    public Purchase addPurchase(Purchase purchase) {
        purchases.add(purchase);
        return purchase;
    }

    public Purchase updatePurchase(String id, Purchase updatedPurchase) {
        for (int i = 0; i < purchases.size(); i++) {
            if (purchases.get(i).getId().equals(id)) {
                purchases.set(i, updatedPurchase);
                return updatedPurchase;
            }
        }
        // Dacă nu există, o adăugăm
        purchases.add(updatedPurchase);
        return updatedPurchase;
    }

    public boolean deletePurchase(String id) {
        for (int i = 0; i < purchases.size(); i++) {
            if (purchases.get(i).getId().equals(id)) {
                purchases.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<Purchase> getPurchasesByCustomer(String customerId) {
        List<Purchase> result = new ArrayList<>();
        for (Purchase p : purchases) {
            if (p.getCustomerId().equals(customerId)) {
                result.add(p);
            }
        }
        return result;
    }

    public List<Purchase> getPurchasesByShop(String shopId) {
        List<Purchase> result = new ArrayList<>();
        for (Purchase p : purchases) {
            if (p.getShopId().equals(shopId)) {
                result.add(p);
            }
        }
        return result;
    }

    public double getTotalAmountByCustomer(String customerId) {
        double total = 0.0;
        for (Purchase p : purchases) {
            if (p.getCustomerId().equals(customerId)) {
                total += p.getAmount();
            }
        }
        return total;
    }

    public double getTotalSalesByShop(String shopId) {
        double total = 0.0;
        for (Purchase p : purchases) {
            if (p.getShopId().equals(shopId)) {
                total += p.getAmount();
            }
        }
        return total;
    }
}
