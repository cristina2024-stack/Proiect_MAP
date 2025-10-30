package com.example.mall_management.controller;

import com.example.mall_management.model.Purchase;
import com.example.mall_management.model.Shop;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shops")
public class ShopController {

    private List<Shop> shops = new ArrayList<>();


    @GetMapping
    public List<Shop> getAllShops() {
        return shops;
    }


    @GetMapping("/{id}")
    public Optional<Shop> getShopById(@PathVariable String id) {
        return shops.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }


    @PostMapping
    public Shop addShop(@RequestBody Shop shop) {
        shops.add(shop);
        return shop;
    }


    @PutMapping("/{id}")
    public Shop updateShop(@PathVariable String id, @RequestBody Shop updatedShop) {
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

    @DeleteMapping("/{id}")
    public String deleteShop(@PathVariable String id) {
        boolean removed = shops.removeIf(s -> s.getId().equals(id));
        return removed ? "Shop deleted successfully." : "Shop not found.";
    }


    @PostMapping("/{id}/purchases")
    public String addPurchase(@PathVariable String id, @RequestBody Purchase purchase) {
        Optional<Shop> shopOpt = shops.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();

        if (shopOpt.isPresent()) {
            shopOpt.get().addPurchase(purchase);
            return "Purchase added successfully.";
        } else {
            return "Shop not found.";
        }
    }

    @GetMapping("/{id}/purchases")
    public List<Purchase> getPurchases(@PathVariable String id) {
        Optional<Shop> shopOpt = shops.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();

        return shopOpt.map(Shop::getPurchases).orElseGet(ArrayList::new);
    }
}

