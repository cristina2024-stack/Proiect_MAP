package com.example.mall_management.controller;

import com.example.mall_management.model.Purchase;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    private List<Purchase> purchases = new ArrayList<>();

    @GetMapping
    public List<Purchase> getAllPurchases() {
        return purchases;
    }

    @GetMapping("/{id}")
    public Optional<Purchase> getPurchaseById(@PathVariable String id) {
        return purchases.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @PostMapping
    public Purchase addPurchase(@RequestBody Purchase purchase) {
        purchases.add(purchase);
        return purchase;
    }

    @PutMapping("/{id}")
    public Purchase updatePurchase(@PathVariable String id, @RequestBody Purchase updatedPurchase) {
        for (int i = 0; i < purchases.size(); i++) {
            if (purchases.get(i).getId().equals(id)) {
                purchases.set(i, updatedPurchase);
                return updatedPurchase;
            }
        }
        purchases.add(updatedPurchase);
        return updatedPurchase;
    }

    @DeleteMapping("/{id}")
    public String deletePurchase(@PathVariable String id) {
        boolean removed = purchases.removeIf(p -> p.getId().equals(id));
        return removed ? "Purchase deleted successfully." : "Purchase not found.";
    }

    @GetMapping("/customer/{customerId}")
    public List<Purchase> getPurchasesByCustomer(@PathVariable String customerId) {
        List<Purchase> result = new ArrayList<>();
        for (Purchase p : purchases) {
            if (p.getCustomerId().equals(customerId)) {
                result.add(p);
            }
        }
        return result;
    }

    @GetMapping("/shop/{shopId}")
    public List<Purchase> getPurchasesByShop(@PathVariable String shopId) {
        List<Purchase> result = new ArrayList<>();
        for (Purchase p : purchases) {
            if (p.getShopId().equals(shopId)) {
                result.add(p);
            }
        }
        return result;
    }
}

