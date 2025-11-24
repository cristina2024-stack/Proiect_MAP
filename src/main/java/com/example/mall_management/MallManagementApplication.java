package com.example.mall_management;

import com.example.mall_management.model.Customer;
import com.example.mall_management.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class MallManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallManagementApplication.class, args);
    }

    @Bean
    public CommandLineRunner testCustomerRepository(CustomerRepository customerRepository) {
        return args -> {
            System.out.println("=== Test CustomerRepository (citire din customer.json) ===");

            // 1. findAll()
            List<Customer> all = customerRepository.findAll();
            System.out.println("\n--- findAll() ---");
            System.out.println("Număr de clienți în fișier: " + all.size());
            for (Customer c : all) {
                System.out.println(
                        c.getId() + " | " +
                                c.getName() + " | " +
                                c.getEmail() + " | " +
                                c.getCurrency()
                );
            }

            if (all.isEmpty()) {
                System.out.println("\nNu există clienți în customer.json. Adaugă manual câțiva în fișier și rulează din nou.");
                return;
            }

            // Luăm primul client ca să testăm funcțiile pe date reale din fișier
            Customer first = all.get(0);

            // 2. findById()
            System.out.println("\n--- findById(\"" + first.getId() + "\") ---");
            Customer byId = customerRepository.findById(first.getId());
            if (byId != null) {
                System.out.println("Găsit: " + byId.getId() + " | " + byId.getName());
            } else {
                System.out.println("NU a fost găsit clientul cu id " + first.getId());
            }

            // 3. findByEmail()
            if (first.getEmail() != null) {
                System.out.println("\n--- findByEmail(\"" + first.getEmail() + "\") ---");
                Customer byEmail = customerRepository.findByEmail(first.getEmail());
                if (byEmail != null) {
                    System.out.println("Găsit după email: " + byEmail.getId() + " | " + byEmail.getName());
                } else {
                    System.out.println("NU a fost găsit niciun client cu acest email.");
                }
            } else {
                System.out.println("\nClientul primul nu are email -> skip test findByEmail");
            }

            // 4. findByName()
            if (first.getName() != null) {
                System.out.println("\n--- findByName(\"" + first.getName() + "\") ---");
                List<Customer> byName = customerRepository.findByName(first.getName());
                byName.forEach(c ->
                        System.out.println(c.getId() + " | " + c.getName())
                );
            } else {
                System.out.println("\nClientul primul nu are nume -> skip test findByName");
            }

            // 5. findByNameContains()
            if (first.getName() != null && first.getName().length() >= 2) {
                String fragment = first.getName().substring(0, 2);
                System.out.println("\n--- findByNameContains(\"" + fragment + "\") ---");
                List<Customer> byNameContains = customerRepository.findByNameContains(fragment);
                byNameContains.forEach(c ->
                        System.out.println(c.getId() + " | " + c.getName())
                );
            } else {
                System.out.println("\nNumele este prea scurt sau null -> skip test findByNameContains");
            }

            // 6. findByCurrency()
            if (first.getCurrency() != null) {
                System.out.println("\n--- findByCurrency(\"" + first.getCurrency() + "\") ---");
                List<Customer> byCurrency = customerRepository.findByCurrency(first.getCurrency());
                byCurrency.forEach(c ->
                        System.out.println(c.getId() + " | " + c.getName() + " | " + c.getCurrency())
                );
            } else {
                System.out.println("\nClientul primul nu are currency -> skip test findByCurrency");
            }

            // 7. findWithPurchases()
            System.out.println("\n--- findWithPurchases() ---");
            List<Customer> withPurchases = customerRepository.findWithPurchases();
            System.out.println("Clienți cu purchases: " + withPurchases.size());
            withPurchases.forEach(c ->
                    System.out.println(c.getId() + " | " + c.getName() + " are purchases")
            );

            // 8. findWithoutPurchases()
            System.out.println("\n--- findWithoutPurchases() ---");
            List<Customer> withoutPurchases = customerRepository.findWithoutPurchases();
            System.out.println("Clienți fără purchases: " + withoutPurchases.size());
            withoutPurchases.forEach(c ->
                    System.out.println(c.getId() + " | " + c.getName() + " NU are purchases")
            );

            System.out.println("\n=== Sfârșit test CustomerRepository ===");
        };
    }
}
