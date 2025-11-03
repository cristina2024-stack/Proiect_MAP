package com.example.mall_management.repository;

import com.example.mall_management.model.Shop;
import java.util.ArrayList;
import java.util.List;

public class Shoprepository extends InMemoryRepository<Shop> {

    public Shoprepository() {
        super(Shop.class);
    }

    // Găsește un magazin după ID
    public Shop findById(String id) {
        List<Shop> allShops = findAll();
        for (Shop s : allShops) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    // Găsește toate magazinele care au o arie mai mare decât un prag dat
    public List<Shop> findByAreaGreaterThan(double minArea) {
        List<Shop> result = new ArrayList<>();
        for (Shop s : findAll()) {
            if (s.getAreaSqm() > minArea) {
                result.add(s);
            }
        }
        return result;
    }
}
