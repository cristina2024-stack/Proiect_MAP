package com.example.mall_management.repository;

import com.example.mall_management.model.Shop;
import java.util.ArrayList;
import java.util.List;

public class Shoprepository extends InMemoryRepository<Shop> {

    public Shoprepository() {
        super(Shop.class);
    }


    public List<Shop> findByName(String name) {
        List<Shop> result = new ArrayList<>();
        List<Shop> allShops = findAll();

        for (int i = 0; i < allShops.size(); i++) {
            Shop shop = allShops.get(i);
            if (shop.getName() != null && shop.getName().equalsIgnoreCase(name)) {
                result.add(shop);
            }
        }
        return result;
    }


    public List<Shop> findByOwnerName(String ownerName) {
        List<Shop> result = new ArrayList<>();
        List<Shop> allShops = findAll();

        for (int i = 0; i < allShops.size(); i++) {
            Shop shop = allShops.get(i);
            if (shop.getOwnerName() != null && shop.getOwnerName().equalsIgnoreCase(ownerName)) {
                result.add(shop);
            }
        }
        return result;
    }

    public List<Shop> findByAreaGreaterThan(double minArea) {
        List<Shop> result = new ArrayList<>();
        List<Shop> allShops = findAll();

        for (int i = 0; i < allShops.size(); i++) {
            Shop shop = allShops.get(i);
            if (shop.getAreaSqm() > minArea) {
                result.add(shop);
            }
        }
        return result;
    }

    public Shop findOneByName(String name) {
        List<Shop> allShops = findAll();

        for (int i = 0; i < allShops.size(); i++) {
            Shop shop = allShops.get(i);
            if (shop.getName() != null && shop.getName().equalsIgnoreCase(name)) {
                return shop;
            }
        }
        return null;
    }
}
