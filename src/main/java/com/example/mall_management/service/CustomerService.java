
package com.example.mall_management.service;

import com.example.mall_management.model.Customer;
import com.example.mall_management.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(String id) {
        return customerRepository.findById(id);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(String id, Customer updatedCustomer) {
        updatedCustomer.setId(id);
        return customerRepository.save(updatedCustomer);
    }

    public boolean deleteCustomer(String id) {
        return customerRepository.deleteById(id);
    }
}
