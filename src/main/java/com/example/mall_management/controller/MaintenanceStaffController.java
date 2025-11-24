// src/main/java/com/example/mall_management/controller/MaintenanceStaffController.java
package com.example.mall_management.controller;

import com.example.mall_management.model.MaintenanceStaff;
import com.example.mall_management.service.MaintenanceStaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/maintenance-staff")
public class MaintenanceStaffController {

    private final MaintenanceStaffService staffService;

    public MaintenanceStaffController(MaintenanceStaffService staffService) {
        this.staffService = staffService;
    }

    // LISTARE PERSONAL
    @GetMapping
    public String list(Model model) {
        model.addAttribute("staffList", staffService.getAllStaff());
        // => src/main/resources/templates/maintenance-staff/index.html
        return "maintenance-staff/index";
    }

    // FORMULAR CREARE
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("staffForm", new MaintenanceStaff());
        model.addAttribute("types", MaintenanceStaff.Type.values());
        // => src/main/resources/templates/maintenance-staff/form.html
        return "maintenance-staff/form";
    }

    // CREARE ANGAJAT
    @PostMapping
    public String create(@ModelAttribute("staffForm") MaintenanceStaff form) {
        // form are deja id, name, type setate din formular
        staffService.addStaff(form);
        return "redirect:/maintenance-staff";
    }

    // ȘTERGERE ANGAJAT
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        staffService.deleteStaff(id);
        return "redirect:/maintenance-staff";
    }
}
