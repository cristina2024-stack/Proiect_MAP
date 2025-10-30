package com.example.mall_management.controller;

import com.example.mall_management.model.StaffAssignment;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/staff-assignments")
public class StaffAssignmentController {

    private List<StaffAssignment> assignments = new ArrayList<>();

    @GetMapping
    public List<StaffAssignment> getAllAssignments() {
        return assignments;
    }

    @GetMapping("/{id}")
    public Optional<StaffAssignment> getAssignmentById(@PathVariable String id) {
        return assignments.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }

    @PostMapping
    public StaffAssignment addAssignment(@RequestBody StaffAssignment assignment) {
        assignments.add(assignment);
        return assignment;
    }

    @PutMapping("/{id}")
    public StaffAssignment updateAssignment(@PathVariable String id, @RequestBody StaffAssignment updatedAssignment) {
        for (int i = 0; i < assignments.size(); i++) {
            if (assignments.get(i).getId().equals(id)) {
                assignments.set(i, updatedAssignment);
                return updatedAssignment;
            }
        }
        // dacă nu există, o adăugăm
        assignments.add(updatedAssignment);
        return updatedAssignment;
    }

    @DeleteMapping("/{id}")
    public String deleteAssignment(@PathVariable String id) {
        boolean removed = assignments.removeIf(a -> a.getId().equals(id));
        return removed ? "Assignment deleted successfully." : "Assignment not found.";
    }

    @GetMapping("/floor/{floorId}")
    public List<StaffAssignment> getAssignmentsByFloor(@PathVariable String floorId) {
        List<StaffAssignment> result = new ArrayList<>();
        for (StaffAssignment a : assignments) {
            if (a.getFloorId().equals(floorId)) {
                result.add(a);
            }
        }
        return result;
    }

    @GetMapping("/staff/{staffId}")
    public List<StaffAssignment> getAssignmentsByStaff(@PathVariable String staffId) {
        List<StaffAssignment> result = new ArrayList<>();
        for (StaffAssignment a : assignments) {
            if (a.getStaffId().equals(staffId)) {
                result.add(a);
            }
        }
        return result;
    }

    @PatchMapping("/{id}/shift")
    public String updateShift(@PathVariable String id, @RequestParam StaffAssignment.Shift shift) {
        Optional<StaffAssignment> assignmentOpt = assignments.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();

        if (assignmentOpt.isPresent()) {
            assignmentOpt.get().setShift(shift);
            return "Shift updated successfully.";
        } else {
            return "Assignment not found.";
        }
    }
}
