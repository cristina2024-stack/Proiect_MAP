// repository/CustomerRepository.java
package com.example.mall_management.repository;

import com.example.mall_management.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CustomerRepository {
    private final Map<String, Customer> store = new ConcurrentHashMap<>();

    public Customer save(Customer customer) {
        Objects.requireNonNull(customer, "Customer cannot be null");
        Objects.requireNonNull(customer.getId(), "Customer ID cannot be null");
        store.put(customer.getId(), customer);
        return customer;
    }

    public List<Customer> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Customer> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    public boolean deleteById(String id) {
        return store.remove(id) != null;
    }
}