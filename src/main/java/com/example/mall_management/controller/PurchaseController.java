package com.example.mall_management.controller;

import com.example.mall_management.model.Purchase;
import com.example.mall_management.service.PurchaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping
    public String listPurchases(Model model) {
        model.addAttribute("purchases", purchaseService.getAllPurchases());
        return "purchase/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("purchase", new Purchase());
        return "purchase/form";
    }

    @PostMapping
    public String createPurchase(@ModelAttribute("purchase") Purchase purchase) {
        purchaseService.addPurchase(purchase);
        return "redirect:/purchases";
    }

    @PostMapping("/{id}/delete")
    public String deletePurchase(@PathVariable String id) {
        purchaseService.deletePurchase(id);
        return "redirect:/purchases";
    }
}
