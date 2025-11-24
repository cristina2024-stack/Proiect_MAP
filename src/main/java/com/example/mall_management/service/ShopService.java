package com.example.mall_management.service;

import com.example.mall_management.model.Purchase;
import com.example.mall_management.model.Shop;
import com.example.mall_management.repository.Shoprepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    private final Shoprepository repository;

    public ShopService(Shoprepository repository) {
        this.repository = repository;
    }

    public List<Shop> getAllShops() {
        return repository.findAll();
    }

    public Shop getShopById(String id) {
        return repository.findById(id);
    }

    public Shop addShop(Shop shop) {
        return repository.save(shop);    // face persistenta în JSON
    }

    public Shop updateShop(String id, Shop updatedShop) {
        updatedShop.setId(id);
        return repository.save(updatedShop);  // înlocuiește entry-ul existent
    }

    public boolean deleteShop(String id) {
        return repository.deleteById(id);
    }

    public boolean addPurchaseToShop(String shopId, Purchase purchase) {
        Shop s = repository.findById(shopId);
        if (s == null) return false;

        s.addPurchase(purchase);
        repository.save(s); // actualizează JSON-ul
        return true;
    }

    public List<Purchase> getPurchasesByShop(String shopId) {
        Shop s = repository.findById(shopId);
        return s == null ? List.of() : s.getPurchases();
    }
}
