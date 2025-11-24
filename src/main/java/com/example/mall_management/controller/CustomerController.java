package com.example.mall_management.controller;

import com.example.mall_management.model.Customer;
import com.example.mall_management.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    // constructor injection
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // ================= LISTARE =================
    @GetMapping
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customer/index"; // => templates/customer/index.html
    }

    // ================= FORM CREARE =================
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/form";  // => templates/customer/form.html
    }

    // ================= CREARE CLIENT =================
    @PostMapping
    public String createCustomer(@ModelAttribute("customer") Customer customer,
                                 Model model) {
        try {
            customerService.createCustomer(customer);
            return "redirect:/customers";
        } catch (Exception ex) {
            // de exemplu: email duplicat sau alte reguli de business
            model.addAttribute("customer", customer);
            model.addAttribute("errorMessage", ex.getMessage());
            return "customer/form"; // reafișează formularul cu mesajul de eroare
        }
    }

    // ================= ȘTERGERE CLIENT =================
    @PostMapping("/{id}/delete")
    public String deleteCustomer(@PathVariable String id, Model model) {
        try {
            customerService.deleteCustomer(id);
            return "redirect:/customers";
        } catch (Exception ex) {
            // dacă nu s-a putut șterge, reafișăm lista cu mesaj de eroare
            model.addAttribute("customers", customerService.getAllCustomers());
            model.addAttribute("errorMessage", ex.getMessage());
            return "customer/index";
        }
    }
}
