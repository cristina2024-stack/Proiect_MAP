package com.example.mall_management.repository;

import com.example.mall_management.model.Purchase;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class Purchase_Repository extends InFileRepository<Purchase> {

    public Purchase_Repository() {
        super(
                "src/main/resources/data/purchase.json",
                new EntityAdapter<Purchase>() {
                    @Override
                    public String getId(Purchase p) {
                        return p.getId();
                    }

                    @Override
                    public void setId(Purchase p, String id) {
                        p.setId(id);
                    }

                    @Override
                    public void validate(Purchase p) {
                        // Validări opționale
                        if (p.getAmount() < 0)
                            throw new IllegalArgumentException("Amount cannot be negative");
                    }
                }
        );
    }

    // ------------------- METODE PERSONALIZATE -------------------

    public List<Purchase> findByCustomerId(String customerId) {
        List<Purchase> result = new ArrayList<>();
        for (Purchase p : findAll()) {
            if (p.getCustomerId() != null && p.getCustomerId().equals(customerId)) {
                result.add(p);
            }
        }
        return result;
    }

    public List<Purchase> findByShopId(String shopId) {
        List<Purchase> result = new ArrayList<>();
        for (Purchase p : findAll()) {
            if (p.getShopId() != null && p.getShopId().equals(shopId)) {
                result.add(p);
            }
        }
        return result;
    }

    public List<Purchase> findByCurrency(String currency) {
        List<Purchase> result = new ArrayList<>();
        for (Purchase p : findAll()) {
            if (p.getCurrency() != null && p.getCurrency().equalsIgnoreCase(currency)) {
                result.add(p);
            }
        }
        return result;
    }

    public List<Purchase> findAboveAmount(double minAmount) {
        List<Purchase> result = new ArrayList<>();
        for (Purchase p : findAll()) {
            if (p.getAmount() > minAmount) {
                result.add(p);
            }
        }
        return result;
    }
}
