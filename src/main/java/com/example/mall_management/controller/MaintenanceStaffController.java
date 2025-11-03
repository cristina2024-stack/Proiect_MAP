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

    @GetMapping
    public String list(Model model) {
        model.addAttribute("staffList", staffService.getAllStaff());
        return "maintenance-staff/index";
    }

    // ATENȚIE: numele atributului este "staffForm"
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("staffForm", new MaintenanceStaff());
        model.addAttribute("types", MaintenanceStaff.Type.values());
        return "maintenance-staff/form";
    }

    @PostMapping
    public String create(@ModelAttribute("staffForm") MaintenanceStaff form) {
        MaintenanceStaff s = new MaintenanceStaff(form.getId(), form.getName(), form.getType());
        staffService.addStaff(s);
        return "redirect:/maintenance-staff";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        staffService.deleteStaff(id);
        return "redirect:/maintenance-staff";
    }
}
