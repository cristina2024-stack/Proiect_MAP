package com.example.mall_management.controller;

import com.example.mall_management.model.Purchase;
import com.example.mall_management.service.PurchaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//Cand vine o cerere catre URL-ul (/purchases) apeleaza metodele corespunzatoare din acest controller
@Controller
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    //Se folosește de obicei pentru citirea datelor (ex: listarea tuturor entităților, obținerea unui element după ID etc.).
    @GetMapping
    public String listPurchases(Model model) {
        model.addAttribute("purchases", purchaseService.getAllPurchases());
        return "purchase/index"; // templates/purchase/index.html
    }


    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("purchase", new Purchase());
        return "purchase/form"; // templates/purchase/form.html
    }
    //Preia datele din corpul cererii (request body) — de obicei trimise ca JSON.
    //
    //Apelează metoda din controller care procesează acele date.
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
