package com.example.mall_management.controller;

import com.example.mall_management.model.Floor;
import com.example.mall_management.service.FloorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/floors")
public class FloorController {

    private final FloorService floorService;

    public FloorController(FloorService floorService) {
        this.floorService = floorService;
    }

    @GetMapping
    public String listFloors(Model model) {
        model.addAttribute("floors", floorService.getAllFloors());
        return "floor/index"; // templates/floor/index.html
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("floor", new Floor());
        return "floor/form"; // templates/floor/form.html
    }

    @PostMapping
    public String createFloor(@ModelAttribute("floor") Floor floor) {
        floorService.addFloor(floor);
        return "redirect:/floors";
    }


    @PostMapping("/{id}/delete")
    public String deleteFloor(@PathVariable String id) {
        floorService.deleteFloor(id);
        return "redirect:/floors";
    }
}
