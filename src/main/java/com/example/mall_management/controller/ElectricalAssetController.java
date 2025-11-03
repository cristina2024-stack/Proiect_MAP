package com.example.mall_management.controller;

import com.example.mall_management.model.ElectricalAsset;
import com.example.mall_management.service.ElectricalAssetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/electrical-assets")
public class ElectricalAssetController {

    private final ElectricalAssetService assetService;

    public ElectricalAssetController(ElectricalAssetService assetService) {
        this.assetService = assetService;
    }


    @GetMapping
    public String listAssets(Model model) {
        model.addAttribute("assets", assetService.getAllAssets());
        return "electrical-asset/index"; // templates/electrical-asset/index.html
    }


    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("asset", new ElectricalAsset());
        model.addAttribute("types", ElectricalAsset.Type.values());
        model.addAttribute("statuses", ElectricalAsset.AssetStatus.values());
        return "electrical-asset/form"; // templates/electrical-asset/form.html
    }


    @PostMapping
    public String createAsset(@ModelAttribute("asset") ElectricalAsset asset) {
        assetService.addAsset(asset);
        return "redirect:/electrical-assets";
    }


    @PostMapping("/{id}/delete")
    public String deleteAsset(@PathVariable String id) {
        assetService.deleteAsset(id);
        return "redirect:/electrical-assets";
    }
}
