package com.example.mall_management.service;

import com.example.mall_management.model.StaffAssignment;
import com.example.mall_management.repository.StaffAssignment_Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffAssignmentService {

    private final StaffAssignment_Repository repository;

    // Constructor injection (folosește bean-ul Spring, nu 'new')
    public StaffAssignmentService(StaffAssignment_Repository repository) {
        this.repository = repository;
    }

    public StaffAssignment addAssignment(StaffAssignment assignment) {
        return repository.save(assignment);
    }

    // Update pe baza ID-ului din entitate (trebuie să fie setat)
    public StaffAssignment updateAssignment(StaffAssignment assignment) {
        return repository.update(assignment); // metoda este în InFileRepository
    }

    // Upsert (creează dacă nu există, altfel înlocuiește)
    public StaffAssignment saveOrUpdateAssignment(StaffAssignment assignment) {
        return repository.saveOrUpdate(assignment);
    }

    public boolean deleteAssignment(String id) {
        return repository.deleteById(id);
    }

    public StaffAssignment getAssignmentById(String id) {
        StaffAssignment assignment = repository.findById(id);
        if (assignment == null) {
            throw new IllegalArgumentException("Assignment not found: " + id);
        }
        return assignment;
    }

    public List<StaffAssignment> getAllAssignments() {
        return repository.findAll();
    }

    public List<StaffAssignment> getAssignmentsByFloor(String floorId) {
        return repository.findByFloorId(floorId);
    }

    public List<StaffAssignment> getAssignmentsByStaff(String staffId) {
        return repository.findByStaffId(staffId);
    }

    public List<StaffAssignment> getAssignmentsByShift(StaffAssignment.Shift shift) {
        return repository.findByShift(shift);
    }

    public StaffAssignment getAssignmentByStaffAndShift(String staffId, StaffAssignment.Shift shift) {
        return repository.findByStaffAndShift(staffId, shift);
    }
}
