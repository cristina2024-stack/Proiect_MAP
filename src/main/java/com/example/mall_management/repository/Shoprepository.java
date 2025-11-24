package com.example.mall_management.repository;

import com.example.mall_management.model.Shop;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class Shoprepository extends InFileRepository<Shop> {

    public Shoprepository() {
        // ATENȚIE:
        //  - presupunem că InFileRepository are un constructor de forma:
        //        InFileRepository(String resourcePath, Class<T> entityClass)
        //  - și că încarcă fișierul din classpath (ex: resources/data/Shop.json)
        super("data/Shop.json", Shop.class);
    }

    // ------------------- METODE PERSONALIZATE -------------------

    public List<Shop> findByName(String name) {
        List<Shop> result = new ArrayList<>();
        for (Shop shop : findAll()) {
            if (shop.getName() != null && shop.getName().equalsIgnoreCase(name)) {
                result.add(shop);
            }
        }
        return result;
    }

    public List<Shop> findByOwnerName(String ownerName) {
        List<Shop> result = new ArrayList<>();
        for (Shop shop : findAll()) {
            if (shop.getOwnerName() != null && shop.getOwnerName().equalsIgnoreCase(ownerName)) {
                result.add(shop);
            }
        }
        return result;
    }

    public List<Shop> findByAreaGreaterThan(double minArea) {
        List<Shop> result = new ArrayList<>();
        for (Shop shop : findAll()) {
            if (shop.getAreaSqm() > minArea) {
                result.add(shop);
            }
        }
        return result;
    }

    public Shop findOneByName(String name) {
        for (Shop shop : findAll()) {
            if (shop.getName() != null && shop.getName().equalsIgnoreCase(name)) {
                return shop;
            }
        }
        return null;
    }
}
