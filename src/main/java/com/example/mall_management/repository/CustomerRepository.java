package com.example.mall_management.repository;

import com.example.mall_management.model.Customer;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository extends InFileRepository<Customer> {

    public CustomerRepository() {
        super(
                "src/main/resources/data/customer.json",   // fișierul JSON pentru Customer
                new EntityAdapter<Customer>() {
                    @Override
                    public String getId(Customer c) {
                        return c.getId();
                    }

                    @Override
                    public void setId(Customer c, String id) {
                        c.setId(id);
                    }

                    @Override
                    public void validate(Customer c) {
                    }
                }
        );
    }
}
