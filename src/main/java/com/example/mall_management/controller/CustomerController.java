package com.example.mall_management.controller;

import com.example.mall_management.model.Customer;
import com.example.mall_management.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    // 🔹 Injecție prin constructor (mai curat decât @Autowired pe câmp)
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // ✅ Obține toți clienții
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // ✅ Obține un client după ID
    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable String id) {
        return customerService.getCustomerById(id);
    }

    // ✅ Creează un client nou
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    // ✅ Actualizează un client existent
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable String id, @RequestBody Customer updatedCustomer) {
        return customerService.updateCustomer(id, updatedCustomer);
    }

    // ✅ Șterge un client după ID
    @DeleteMapping("/{id}")
    public boolean deleteCustomer(@PathVariable String id) {
        return customerService.deleteCustomer(id);
    }
}
