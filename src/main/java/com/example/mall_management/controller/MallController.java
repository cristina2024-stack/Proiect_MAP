package com.example.mall_management.controller;
import com.example.mall_management.service.MallService;

import com.example.mall_management.model.Mall;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/malls")
public class MallController {

    private final MallService mallService;

    public MallController(MallService mallService) {
        this.mallService = mallService;
    }

    // LISTĂ MALL-URI
    @GetMapping
    public String listMalls(Model model) {
        model.addAttribute("malls", mallService.getAllMalls());
        return "mall/index";
    }

    // FORMULAR CREARE
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("mall", new Mall());
        return "mall/form";
    }

    // CREARE MALL
    @PostMapping
    public String createMall(@ModelAttribute Mall mall) {
        mallService.addMall(mall);
        return "redirect:/malls";
    }

    // ȘTERGERE MALL
    @PostMapping("/{id}/delete")
    public String deleteMall(@PathVariable String id) {
        mallService.deleteMall(id);
        return "redirect:/malls";
    }
}
