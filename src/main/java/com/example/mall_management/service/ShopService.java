package com.example.mall_management.service;

import com.example.mall_management.model.Purchase;
import com.example.mall_management.model.Shop;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShopService {

    // "Bază de date" simplă în memorie
    private List<Shop> shops = new ArrayList<>();

    public List<Shop> getAllShops() {
        return shops;
    }

    public Optional<Shop> getShopById(String id) {
        for (Shop s : shops) {
            if (s.getId().equals(id)) {
                return Optional.of(s);
            }
        }
        return Optional.empty();
    }

    public Shop addShop(Shop shop) {
        shops.add(shop);
        return shop;
    }

    public Shop updateShop(String id, Shop updatedShop) {
        for (int i = 0; i < shops.size(); i++) {
            if (shops.get(i).getId().equals(id)) {
                shops.set(i, updatedShop);
                return updatedShop;
            }
        }
        // dacă nu există, îl adăugăm
        shops.add(updatedShop);
        return updatedShop;
    }

    public boolean deleteShop(String id) {
        for (int i = 0; i < shops.size(); i++) {
            if (shops.get(i).getId().equals(id)) {
                shops.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean addPurchaseToShop(String shopId, Purchase purchase) {
        for (Shop s : shops) {
            if (s.getId().equals(shopId)) {
                s.addPurchase(purchase);
                return true;
            }
        }
        return false;
    }

    public List<Purchase> getPurchasesByShop(String shopId) {
        for (Shop s : shops) {
            if (s.getId().equals(shopId)) {
                return s.getPurchases();
            }
        }
        return new ArrayList<>();
    }
}
