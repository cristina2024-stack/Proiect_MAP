package com.example.mall_management.controller;

import com.example.mall_management.model.Security_Staff;
import com.example.mall_management.service.SecurityStaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/security-staff")
public class SecurityStaffController {

    private final SecurityStaffService securityService;

    public SecurityStaffController(SecurityStaffService securityService) {
        this.securityService = securityService;
    }

    @GetMapping
    public String listSecurityStaff(Model model) {
        model.addAttribute("securityList", securityService.getAllSecurityStaff());
        return "security-staff/index"; // templates/security-staff/index.html
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("securityStaff", new Security_Staff());
        return "security-staff/form"; // templates/security-staff/form.html
    }

    @PostMapping
    public String createSecurityStaff(@ModelAttribute("securityStaff") Security_Staff staff) {
        securityService.addSecurityStaff(staff);
        return "redirect:/security-staff";
    }

    @PostMapping("/{id}/delete")
    public String deleteSecurityStaff(@PathVariable String id) {
        securityService.deleteSecurityStaff(id);
        return "redirect:/security-staff";
    }
}
