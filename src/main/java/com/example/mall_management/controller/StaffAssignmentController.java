package com.example.mall_management.controller;

import com.example.mall_management.model.StaffAssignment;
import com.example.mall_management.service.StaffAssignmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/staff-assignments")
public class StaffAssignmentController {

    private final StaffAssignmentService assignmentService;

    public StaffAssignmentController(StaffAssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }


    @GetMapping
    public String listAssignments(Model model) {
        model.addAttribute("assignments", assignmentService.getAllAssignments());
        return "staff-assignment/index"; // templates/staff-assignment/index.html
    }


    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("assignment", new StaffAssignment());
        model.addAttribute("shifts", StaffAssignment.Shift.values());
        return "staff-assignment/form"; // templates/staff-assignment/form.html
    }

    @PostMapping
    public String createAssignment(@ModelAttribute("assignment") StaffAssignment assignment) {
        assignmentService.addAssignment(assignment);
        return "redirect:/staff-assignments";
    }

    @PostMapping("/{id}/delete")
    public String deleteAssignment(@PathVariable String id) {
        assignmentService.deleteAssignment(id);
        return "redirect:/staff-assignments";
    }
}
