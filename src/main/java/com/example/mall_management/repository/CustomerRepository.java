package com.example.mall_management.repository;

import com.example.mall_management.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepository extends InFileRepository<Customer> {

    public CustomerRepository() {
        // fișierul este în src/main/resources/data/customer.json
        super("data/customer.json", Customer.class);
    }

    public Customer findByEmail(String email) {
        if (email == null) return null;
        for (Customer c : findAll()) {
            if (c.getEmail() != null && c.getEmail().equalsIgnoreCase(email)) {
                return c;
            }
        }
        return null;
    }

    public List<Customer> findByName(String name) {
        List<Customer> result = new ArrayList<>();
        if (name == null) return result;

        for (Customer c : findAll()) {
            if (c.getName() != null && c.getName().equalsIgnoreCase(name)) {
                result.add(c);
            }
        }
        return result;
    }

    public List<Customer> findByNameContains(String text) {
        List<Customer> result = new ArrayList<>();
        if (text == null) return result;

        for (Customer c : findAll()) {
            if (c.getName() != null &&
                    c.getName().toLowerCase().contains(text.toLowerCase())) {
                result.add(c);
            }
        }
        return result;
    }

    public List<Customer> findByCurrency(String currency) {
        List<Customer> result = new ArrayList<>();
        if (currency == null) return result;

        for (Customer c : findAll()) {
            if (c.getCurrency() != null &&
                    c.getCurrency().equalsIgnoreCase(currency)) {
                result.add(c);
            }
        }
        return result;
    }

    public List<Customer> findWithPurchases() {
        List<Customer> result = new ArrayList<>();

        for (Customer c : findAll()) {
            if (c.getPurchases() != null && !c.getPurchases().isEmpty()) {
                result.add(c);
            }
        }
        return result;
    }

    public List<Customer> findWithoutPurchases() {
        List<Customer> result = new ArrayList<>();

        for (Customer c : findAll()) {
            if (c.getPurchases() == null || c.getPurchases().isEmpty()) {
                result.add(c);
            }
        }
        return result;
    }
}
