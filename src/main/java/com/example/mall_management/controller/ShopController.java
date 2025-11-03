package com.example.mall_management.controller;

import com.example.mall_management.model.Shop;
import com.example.mall_management.service.ShopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/shops")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }


    @GetMapping
    public String listShops(Model model) {
        model.addAttribute("shops", shopService.getAllShops());
        return "shop/index";
    }


    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("shop", new Shop());
        return "shop/form";
    }


    @PostMapping
    public String createShop(@ModelAttribute("shop") Shop shop) {
        shopService.addShop(shop);
        return "redirect:/shops";
    }

    @PostMapping("/{id}/delete")
    public String deleteShop(@PathVariable String id) {
        shopService.deleteShop(id);
        return "redirect:/shops";
    }
    //Nu căuta un fișier HTML sau o pagină JSP cu numele rezultatului — trimite direct conținutul returnat în corpul răspunsului (response body).”
    @GetMapping("/ping")
    @ResponseBody
    public String ping() {
        return "pong";
    }

}

