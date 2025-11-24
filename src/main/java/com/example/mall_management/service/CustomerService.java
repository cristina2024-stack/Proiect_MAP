package com.example.mall_management.service;

import com.example.mall_management.model.Customer;
import com.example.mall_management.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    // Constructor injection
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // ----------------- CRUD -----------------

    public List<Customer> getAllCustomers() {
        // citește toți clienții din fișierul JSON via InFileRepository
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(String id) {
        // findById din InFileRepository întoarce Customer sau null
        Customer customer = customerRepository.findById(id);
        return Optional.ofNullable(customer);
    }

    /**
     * Creare client NOU.
     * Aici poți pune reguli de business, de ex: email unic.
     */
    public Customer createCustomer(Customer customer) {

        // Exemplu de validare de business: email-ul trebuie să fie unic
        if (customer.getEmail() != null) {
            Customer existing = customerRepository.findByEmail(customer.getEmail());
            if (existing != null) {
                // controller-ul poate prinde această excepție și afișa mesaj în pagină
                throw new IllegalArgumentException("Există deja un client cu acest email.");
            }
        }

        return customerRepository.save(customer);
    }

    /**
     * alias pentru createCustomer, dacă îl folosești în alte părți
     */
    public Customer addCustomer(Customer customer) {
        return createCustomer(customer);
    }

    /**
     * Actualizare client EXISTENT.
     */
    public Customer updateCustomer(String id, Customer updatedCustomer) {

        Customer existing = customerRepository.findById(id);
        if (existing == null) {
            throw new IllegalArgumentException("Clientul nu a fost găsit.");
        }

        // verificăm email-ul doar dacă îl schimbi
        if (updatedCustomer.getEmail() != null &&
                !updatedCustomer.getEmail().equalsIgnoreCase(existing.getEmail())) {

            Customer other = customerRepository.findByEmail(updatedCustomer.getEmail());
            if (other != null) {
                throw new IllegalArgumentException("Există deja un alt client cu acest email.");
            }
        }

        // ne asigurăm că ID-ul rămâne cel vechi
        updatedCustomer.setId(id);

        // aici save(...) din InFileRepository face practic "upsert" (salvează lista în JSON)
        return customerRepository.save(updatedCustomer);
    }

    /**
     * Ștergere client.
     */
    public void deleteCustomer(String id) {
        boolean deleted = customerRepository.deleteById(id);
        if (!deleted) {
            throw new IllegalArgumentException("Clientul cu acest ID nu există sau nu a putut fi șters.");
        }
    }
}
