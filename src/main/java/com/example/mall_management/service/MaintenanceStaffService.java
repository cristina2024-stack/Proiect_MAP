package com.example.mall_management.service;

import com.example.mall_management.model.MaintenanceStaff;
import com.example.mall_management.model.StaffAssignment;
import com.example.mall_management.repository.MaintenanceStaff_Repository;
import com.example.mall_management.repository.StaffAssignment_Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceStaffService {

    private final MaintenanceStaff_Repository staffRepository;
    private final StaffAssignment_Repository assignmentRepository;

    public MaintenanceStaffService(MaintenanceStaff_Repository staffRepository,
                                   StaffAssignment_Repository assignmentRepository) {
        this.staffRepository = staffRepository;
        this.assignmentRepository = assignmentRepository;
    }

    // ---------------- CRUD de bază ----------------

    public List<MaintenanceStaff> getAllStaff() {
        return staffRepository.findAll();
    }

    public Optional<MaintenanceStaff> getStaffById(String id) {
        // findById din InFileRepository întoarce MaintenanceStaff sau null
        MaintenanceStaff staff = staffRepository.findById(id);
        return Optional.ofNullable(staff);
    }

    public MaintenanceStaff addStaff(MaintenanceStaff staff) {
        return staffRepository.save(staff);
    }

    public MaintenanceStaff updateStaff(String id, MaintenanceStaff updatedStaff) {
        updatedStaff.setId(id);
        // InFileRepository are update(T entity), nu update(id, entity)
        return staffRepository.update(updatedStaff);
    }

    public boolean deleteStaff(String id) {
        return staffRepository.deleteById(id);
    }

    // ---------------- Filtrări ----------------

    public List<MaintenanceStaff> getStaffByType(MaintenanceStaff.Type type) {
        return staffRepository.findByType(type);
    }

    // ---------------- Asignări ----------------

    public boolean addAssignmentToStaff(String staffId, StaffAssignment assignment) {
        // căutăm staff-ul
        MaintenanceStaff staff = staffRepository.findById(staffId);
        if (staff == null) {
            return false;
        }

        // ne asigurăm că assignment are ID și există în repo
        if (assignment.getId() == null || assignment.getId().isBlank()) {
            assignmentRepository.save(assignment);
        } else {
            StaffAssignment existing = assignmentRepository.findById(assignment.getId());
            if (existing == null) {
                assignmentRepository.save(assignment);
            }
        }

        // adăugăm ID-ul asignării la listă
        if (staff.getAssignments() == null) {
            staff.setAssignments(new ArrayList<>());
        }
        staff.getAssignments().add(assignment.getId());

        // salvăm modificările
        staffRepository.update(staff);

        return true;
    }

    public List<StaffAssignment> getAssignmentsByStaff(String staffId) {
        MaintenanceStaff staff = staffRepository.findById(staffId);
        if (staff == null || staff.getAssignments() == null) {
            return new ArrayList<>();
        }

        List<StaffAssignment> result = new ArrayList<>();

        for (String assignmentId : staff.getAssignments()) {
            StaffAssignment a = assignmentRepository.findById(assignmentId);
            if (a != null) {
                result.add(a);
            }
        }

        return result;
    }
}
