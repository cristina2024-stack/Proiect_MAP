package com.example.mall_management.controller;

import com.example.mall_management.model.MaintenanceStaff;
import com.example.mall_management.model.StaffAssignment;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/maintenance-staff")
public class MaintenanceStaffController {

    private List<MaintenanceStaff> staffList = new ArrayList<>();

    @GetMapping
    public List<MaintenanceStaff> getAllStaff() {
        return staffList;
    }

    @GetMapping("/{id}")
    public Optional<MaintenanceStaff> getStaffById(@PathVariable String id) {
        return staffList.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }


    @PostMapping
    public MaintenanceStaff addStaff(@RequestBody MaintenanceStaff staff) {
        staffList.add(staff);
        return staff;
    }

    @PutMapping("/{id}")
    public MaintenanceStaff updateStaff(@PathVariable String id, @RequestBody MaintenanceStaff updatedStaff) {
        for (int i = 0; i < staffList.size(); i++) {
            if (staffList.get(i).getId().equals(id)) {
                staffList.set(i, updatedStaff);
                return updatedStaff;
            }
        }
        // Dacă nu există, adăugăm noul staff
        staffList.add(updatedStaff);
        return updatedStaff;
    }

    @DeleteMapping("/{id}")
    public String deleteStaff(@PathVariable String id) {
        boolean removed = staffList.removeIf(s -> s.getId().equals(id));
        return removed ? "Staff deleted successfully." : "Staff not found.";
    }
    @PostMapping("/{id}/assignments")
    public String addAssignment(@PathVariable String id, @RequestBody StaffAssignment assignment) {
        Optional<MaintenanceStaff> staffOpt = staffList.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();

        if (staffOpt.isPresent()) {
            staffOpt.get().addAssignment(assignment);
            return "Assignment added successfully.";
        } else {
            return "Staff not found.";
        }
    }
    @GetMapping("/{id}/assignments")
    public List<StaffAssignment> getAssignments(@PathVariable String id) {
        Optional<MaintenanceStaff> staffOpt = staffList.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();

        return staffOpt.map(MaintenanceStaff::getAssignments).orElseGet(ArrayList::new);
    }
}
